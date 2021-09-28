import dto.CustomFieldDTO;
import dto.DocumentDTO;
import dto.template.FieldBlockDTO;
import dto.template.TemplateDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import resource.CustomFieldResource;
import resource.DocumentResource;
import resource.FieldBlockResource;
import resource.TemplateResource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class SDSapi implements CustomFieldResource, DocumentResource, FieldBlockResource, TemplateResource
{
    private String token;
    private String XSRFTOKEN;
    private static String domain = "http://127.0.0.1:8080";

    public SDSapi(String password, String username, String domain)
    {
        this.domain = domain;
        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response = null;

        HttpRequest getXSRFTokenRequest = HttpRequest.newBuilder()
                .uri(URI.create(domain + "/login"))
                .timeout(Duration.ofMinutes(1))
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate, br")
                .GET()
                .build();

        try
        {
            response = client.send(getXSRFTokenRequest, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        assert response != null;
        XSRFTOKEN = response.headers().allValues("Set-Cookie").toString().replace("[XSRF-TOKEN=", "").replace("; path=/]", "");

        System.out.println("XSRF-TOKEN = " + XSRFTOKEN);

        HttpRequest getTokenRequest = HttpRequest.newBuilder()
                .uri(URI.create(domain + "/api/authentication?username=" + username + "&password=" + password + "&remember-me=false&submit=Login"))
                .timeout(Duration.ofMinutes(1))
                .header("X-XSRF-TOKEN", XSRFTOKEN)
                .header("Cookie", "XSRF-TOKEN=" + XSRFTOKEN)
                .POST(HttpRequest.BodyPublishers.ofByteArray("".getBytes()))
                .build();

        try
        {
            response = client.send(getTokenRequest, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        token = response.headers().allValues("Set-Cookie").toString().split(";")[0].replace("[JSESSIONID=", "");
        System.out.println("JSESSIONID = " + token);
    }

    public SDSapi(String password, String username)
    {
        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response = null;

        HttpRequest getXSRFTokenRequest = HttpRequest.newBuilder()
                .uri(URI.create(domain + "/login"))
                .timeout(Duration.ofMinutes(1))
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate, br")
                .GET()
                .build();

        try
        {
            response = client.send(getXSRFTokenRequest, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        assert response != null;
        XSRFTOKEN = response.headers().allValues("Set-Cookie").toString().replace("[XSRF-TOKEN=", "").replace("; path=/]", "");

        System.out.println("XSRF-TOKEN = " + XSRFTOKEN);

        HttpRequest getTokenRequest = HttpRequest.newBuilder()
                .uri(URI.create(domain + "/api/authentication?username=" + username + "&password=" + password + "&remember-me=false&submit=Login"))
                .timeout(Duration.ofMinutes(1))
                .header("X-XSRF-TOKEN", XSRFTOKEN)
                .header("Cookie", "XSRF-TOKEN=" + XSRFTOKEN)
                .POST(HttpRequest.BodyPublishers.ofByteArray("".getBytes()))
                .build();

        try
        {
            response = client.send(getTokenRequest, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        token = response.headers().allValues("Set-Cookie").toString().split(";")[0].replace("[JSESSIONID=", "");
        System.out.println("JSESSIONID = " + token);
    }

    public static byte[] serializeObject(Object obj) throws IOException
    {
        ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bytesOut);
        oos.writeObject(obj);
        oos.flush();
        byte[] bytes = bytesOut.toByteArray();
        bytesOut.close();
        oos.close();
        return bytes;
    }

    private HttpResponse doAPIJSONRequest(String endpoint, int requestType, String requestBody)
    {
        System.out.println("=================\nRequest:\n" + endpoint);
        System.out.println(requestBody+"\n");
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(domain + endpoint))
                .timeout(Duration.ofMinutes(10))
                .header("Content-Type", "application/json")
                .header("X-XSRF-TOKEN", this.XSRFTOKEN)
                .header("Cookie", "XSRF-TOKEN=" + this.XSRFTOKEN + "; " + "JSESSIONID=" + this.token);

        HttpRequest request;

        if(requestType == REQUESTTYPE.REQUESTTYPE_POST)
        {
            request = requestBuilder.POST(HttpRequest.BodyPublishers.ofByteArray(requestBody.getBytes()))
                    .build();
        } else if(requestType == REQUESTTYPE.REQUESTTYPE_GET)
        {
            request = requestBuilder.GET()
                    .build();
        } else if(requestType == REQUESTTYPE.REQUESTTYPE_PUT)
        {
            request = requestBuilder.PUT(HttpRequest.BodyPublishers.ofByteArray(requestBody.getBytes()))
                    .build();
        } else if(requestType == REQUESTTYPE.REQUESTTYPE_DELETE)
        {
            request = requestBuilder.DELETE()
                    .build();
        } else return null;

        HttpResponse<String> response = null;

        try
        {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println("Response:\n" + response.body() + "\n=================");

        return response;
    }

    String getSDSFormatedDate(LocalDateTime formattable)
    {
        return DateTimeFormatter
                .ofPattern("yyyy-MM-dd'T'HH:mm:ss")
                .format(formattable);
    }

    String getSDSFormatedDate(Date formattable)
    {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(formattable);
    }

    void handleNonSuccessfullResponse(HttpResponse response)
    {
        System.out.println(response);
    }

    @Override
    public CustomFieldDTO createCustomField( String description, String name, String type )
    {
        HttpResponse response = doAPIJSONRequest("/api/customFields", REQUESTTYPE.REQUESTTYPE_POST,
                new JSONObject().put("description", description)
                                .put("id", "null")
                                .put("name", name)
                                .put("type", type).toString());


        if(response != null)
        {
            if (response.statusCode() == 200 ||
                response.statusCode() == 201)
            {
                return new CustomFieldDTO(new JSONObject(String.valueOf(response.body())).getLong("id"),
                                          new JSONObject(String.valueOf(response.body())).getString("name"),
                                          new JSONObject(String.valueOf(response.body())).getString("description"),
                                          new JSONObject(String.valueOf(response.body())).getString("customFieldType"));
            } else
            {
                handleNonSuccessfullResponse(response);
                return null;
            }
        } else return null;
    }

    @Override
    public String setCustomFieldValue( Long customFieldId, Long documentId, String value )
    {
        HttpResponse response = doAPIJSONRequest(
                "/api/customFields/value?customFieldId=" + customFieldId +
                         "&documentId=" + documentId + "&value="+value, REQUESTTYPE.REQUESTTYPE_PUT, "");


        if(response != null)
        {
            if (response.statusCode() == 200 ||
                    response.statusCode() == 201)
            {
                return value;
            } else
            {
                handleNonSuccessfullResponse(response);
                return null;
            }
        } else return null;
    }

    @Override
    public String getCustomFieldValue( Long customFieldId, Long documentId )
    {
        HttpResponse response = doAPIJSONRequest(
                "/api/customFields/value?customFieldId=" + customFieldId +
                        "&documentId=" + documentId, REQUESTTYPE.REQUESTTYPE_GET, "");


        if(response != null)
        {
            if (response.statusCode() == 200 ||
                    response.statusCode() == 201)
            {
                return (String) response.body();
            } else
            {
                handleNonSuccessfullResponse(response);
                return null;
            }
        } else return null;
    }

    @Override
    public DocumentDTO createDocument(Long[] assignedTemplates)
    {
        HttpResponse response = doAPIJSONRequest(
                "/api/documents", REQUESTTYPE.REQUESTTYPE_POST,
                new JSONObject().put("assignedTemplates", assignedTemplates)
                                .put("created",
                                        getSDSFormatedDate(LocalDateTime.now())
                                ).toString());


        if(response != null)
        {
            if (response.statusCode() == 200 ||
                    response.statusCode() == 201)
            {
                try
                {
                    return new DocumentDTO(new JSONObject(String.valueOf(response.body())).getLong("id"),
                                           new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX")
                                                   .parse((new JSONObject(String.valueOf(response.body())).getString("created"))));
                } catch (ParseException e)
                {
                    e.printStackTrace();
                    return new DocumentDTO(new JSONObject(String.valueOf(response.body())).getLong("id"),new Date(0));
                }
            } else
            {
                handleNonSuccessfullResponse(response);
                return null;
            }
        } else return null;
    }


    @Override
    public DocumentDTO updateDocument(DocumentDTO document)
    {
        HttpResponse response = doAPIJSONRequest(
                "/api/documents", REQUESTTYPE.REQUESTTYPE_PUT,
                new JSONObject().put("assignedTemplates", document.getAssignedTemplates())
                                .put("created", getSDSFormatedDate(document.getCreated()))
                                .put("id", document.getId()).toString());


        if(response != null)
        {
            if (response.statusCode() == 200 ||
                    response.statusCode() == 201)
            {
                try
                {
                    return new DocumentDTO(new JSONObject(String.valueOf(response.body())).getLong("id"),
                            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX")
                                    .parse((new JSONObject(String.valueOf(response.body())).getString("created"))));
                } catch (ParseException e)
                {
                    e.printStackTrace();
                    return new DocumentDTO(new JSONObject(String.valueOf(response.body())).getLong("id"),new Date(0));
                }
            } else
            {
                handleNonSuccessfullResponse(response);
                return null;
            }
        } else return null;
    }


    @Override
    public DocumentDTO getDocument(Long id)
    {
        HttpResponse response = doAPIJSONRequest(
                "/api/documents/"+id, REQUESTTYPE.REQUESTTYPE_GET, "");


        if(response != null)
        {
            if (response.statusCode() == 200 ||
                    response.statusCode() == 201)
            {
                try
                {
                    return new DocumentDTO(new JSONObject(String.valueOf(response.body())).getLong("id"),
                    new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX")
                                       .parse((new JSONObject(String.valueOf(response.body())).getString("created"))));
                } catch (ParseException e)
                {
                    e.printStackTrace();
                    return new DocumentDTO(new JSONObject(String.valueOf(response.body())).getLong("id"),new Date(0));
                }
            } else
            {
                handleNonSuccessfullResponse(response);
                return null;
            }
        } else return null;
    }


    @Override
    public void deleteDocument(Long id)
    {
        HttpResponse response = doAPIJSONRequest(
                "/api/documents/"+id, REQUESTTYPE.REQUESTTYPE_DELETE, "");

        if(response.statusCode() != 200 &&
           response.statusCode() != 201 &&
           response.statusCode() != 204) handleNonSuccessfullResponse(response);
    }


    @Override
    public DocumentDTO[] getAllDocuments(int pageNumber, int pageSize)
    {
        HttpResponse response = doAPIJSONRequest("/api/documents?page=" + pageNumber +
                "size="+pageSize, REQUESTTYPE.REQUESTTYPE_GET, "");

        if(response != null)
        {
            if (response.statusCode() == 200 ||
                    response.statusCode() == 201)
            {
                List<DocumentDTO> result = new LinkedList<>();
                for (Object o : new JSONArray(String.valueOf(response.body())))
                {
                    JSONObject docJSON = new JSONObject(o.toString());
                    DocumentDTO doc = new DocumentDTO();
                    doc.setId(docJSON.getLong("id"));
                    try
                    {
                        doc.setCreated(
                                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX")
                                        .parse(docJSON.getString("created"))
                                        );
                        doc.setAssignedTemplates((List<Long>)(Object)docJSON.getJSONArray("assignedTemplates").toList());
                        result.add(doc);
                    } catch (ParseException e)
                    {
                        e.printStackTrace();
                        doc.setCreated(null);
                        result.add( doc );
                    }
                }

                return result.toArray(new DocumentDTO[0]);
            } else
            {
                handleNonSuccessfullResponse(response);
                return null;
            }
        } else return null;
    }

    @Override
    public TemplateDTO createTemplate(String description, String name)
    {
        HttpResponse response = doAPIJSONRequest("/api/templates", REQUESTTYPE.REQUESTTYPE_POST,
                new JSONObject().put("description", description)
                        .put("id", "null")
                        .put("name", name).toString());


        if(response != null)
        {
            if (response.statusCode() == 200 ||
                    response.statusCode() == 201)
            {
                return new TemplateDTO(new JSONObject(String.valueOf(response.body())).getLong("id"),
                        new JSONObject(String.valueOf(response.body())).getString("name"),
                        new JSONObject(String.valueOf(response.body())).getString("description"));
            } else
            {
                handleNonSuccessfullResponse(response);
                return null;
            }
        } else return null;
    }

    @Override
    public DocumentDTO assignTemplateToDocument(Long documentId, Long templateId)
    {
        HttpResponse response = doAPIJSONRequest(
                "/api/assignTemplateToDocument?documentId=" + documentId +
                                                      "&templateId=" + templateId,
                REQUESTTYPE.REQUESTTYPE_GET, "");


        if(response != null)
        {
            if (response.statusCode() == 200 ||
                    response.statusCode() == 201)
            {
                return (DocumentDTO) response.body();
            } else
            {
                handleNonSuccessfullResponse(response);
                return null;
            }
        } else return null;
    }

    @Override
    public TemplateDTO addItemToTemplateLayout(Long fieldBlockId, int position, Long templateId)
    {
        HttpResponse response = doAPIJSONRequest(
                "/api/templateLayout?fieldBlockId=" +fieldBlockId+
                                            "&position=" + position +
                                            "&templateId=" + templateId,
                REQUESTTYPE.REQUESTTYPE_PUT, "");


        if(response != null)
        {
            if (response.statusCode() == 200 ||
                    response.statusCode() == 201)
            {
                return new TemplateDTO(new JSONObject(String.valueOf(response.body())).getLong("id"),
                        new JSONObject(String.valueOf(response.body())).getString("name"),
                        new JSONObject(String.valueOf(response.body())).getString("description"));
            } else
            {
                handleNonSuccessfullResponse(response);
                return null;
            }
        } else return null;
    }

    @Override
    public FieldBlockDTO createFieldBlock(String description, String name)
    {
        HttpResponse response = doAPIJSONRequest("/api/fieldBlocks", REQUESTTYPE.REQUESTTYPE_POST,
                new JSONObject().put("description", description)
                        .put("id", "null")
                        .put("name", name).toString());


        if(response != null)
        {
            if (response.statusCode() == 200 ||
                    response.statusCode() == 201)
            {
                return new FieldBlockDTO(new JSONObject(String.valueOf(response.body())).getLong("id"),
                        new JSONObject(String.valueOf(response.body())).getString("name"),
                        new JSONObject(String.valueOf(response.body())).getString("description"));
            } else
            {
                handleNonSuccessfullResponse(response);
                return null;
            }
        } else return null;
    }

    @Override
    public FieldBlockDTO addItemToFieldBlockLayout(Long customFieldId, Long fieldBlockId, int position)
    {
        HttpResponse response = doAPIJSONRequest(
                "/api/fieldBlockLayout?customFieldId="+customFieldId +
                                              "&fieldBlockId=" + fieldBlockId +
                                              "&position="+position,
                            REQUESTTYPE.REQUESTTYPE_PUT, "");


        if(response != null)
        {
            if (response.statusCode() == 200 ||
                    response.statusCode() == 201)
            {
                return new FieldBlockDTO(new JSONObject(String.valueOf(response.body())).getLong("id"),
                        new JSONObject(String.valueOf(response.body())).getString("name"),
                        new JSONObject(String.valueOf(response.body())).getString("description"));
            } else
            {
                handleNonSuccessfullResponse(response);
                return null;
            }
        } else return null;
    }

}
