import dto.CustomFieldDTO;
import dto.DocumentDTO;
import dto.template.FieldBlockDTO;
import dto.template.TemplateDTO;
import interceptor.Interceptor;
import interceptor.JWTAuthInterceptorImpl;
import interceptor.REQUESTTYPE;
import org.json.JSONArray;
import org.json.JSONObject;
import resource.CustomFieldResource;
import resource.DocumentResource;
import resource.FieldBlockResource;
import resource.TemplateResource;
import resource.authentication.AuthenticationResource;
import resource.authentication.JavaSessionAuthenticationResourceImpl;
import resource.authentication.LoginVM;

import javax.security.auth.login.LoginException;
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

public class SDSapi
        implements CustomFieldResource, DocumentResource, FieldBlockResource, TemplateResource
{
    Interceptor interceptor;

    public SDSapi(String password, String username, String domain)
    {
        interceptor = new JWTAuthInterceptorImpl(new LoginVM(username, password, false), domain);
    }

    public SDSapi(String password, String username)
    {
        interceptor = new JWTAuthInterceptorImpl(new LoginVM(username, password, false), null);
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

    String getSDSFormattedDate( LocalDateTime formattable)
    {
        return DateTimeFormatter
                .ofPattern("yyyy-MM-dd'T'HH:mm:ss")
                .format(formattable);
    }

    String getSDSFormattedDate( Date formattable)
    {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(formattable);
    }

    void handleNonSuccessfullResponse(HttpResponse response)
    {
        System.out.println(response);
    }

    @Override
    public CustomFieldDTO createCustomField( String description, String name, String type ) throws LoginException
    {
        HttpResponse response = interceptor.doRequest("/api/customFields", REQUESTTYPE.REQUESTTYPE_POST,
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
                                          new JSONObject(String.valueOf(response.body())).getString("type"));
            } else
            {
                handleNonSuccessfullResponse(response);
                return null;
            }
        } else return null;
    }

    @Override
    public String setCustomFieldValue( Long customFieldId, Long documentId, String value ) throws LoginException
    {
        HttpResponse response = interceptor.doRequest(
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
    public String getCustomFieldValue( Long customFieldId, Long documentId ) throws LoginException
    {
        HttpResponse response = interceptor.doRequest(
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
    public DocumentDTO createDocument(Long[] assignedTemplates) throws LoginException
    {
        HttpResponse response = interceptor.doRequest(
                "/api/documents", REQUESTTYPE.REQUESTTYPE_POST,
                new JSONObject().put("assignedTemplates", assignedTemplates)
                                .put("created",
                                        getSDSFormattedDate(LocalDateTime.now())
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
    public DocumentDTO updateDocument(DocumentDTO document) throws LoginException
    {
        HttpResponse response = interceptor.doRequest(
                "/api/documents", REQUESTTYPE.REQUESTTYPE_PUT,
                new JSONObject().put("assignedTemplates", document.getAssignedTemplates())
                                .put("created", getSDSFormattedDate(document.getCreated()))
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
    public DocumentDTO getDocument(Long id) throws LoginException
    {
        HttpResponse response = interceptor.doRequest(
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
    public void deleteDocument(Long id) throws LoginException
    {
        HttpResponse response = interceptor.doRequest(
                "/api/documents/"+id, REQUESTTYPE.REQUESTTYPE_DELETE, "");

        if(response.statusCode() != 200 &&
           response.statusCode() != 201 &&
           response.statusCode() != 204) handleNonSuccessfullResponse(response);
    }


    @Override
    public DocumentDTO[] getAllDocuments(int pageNumber, int pageSize) throws LoginException
    {
        HttpResponse response = interceptor.doRequest("/api/documents?page=" + pageNumber +
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
    public TemplateDTO createTemplate(String description, String name) throws LoginException
    {
        HttpResponse response = interceptor.doRequest("/api/templates", REQUESTTYPE.REQUESTTYPE_POST,
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
    public DocumentDTO assignTemplateToDocument(Long documentId, Long templateId) throws LoginException
    {
        HttpResponse response = interceptor.doRequest(
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
    public TemplateDTO addItemToTemplateLayout(Long fieldBlockId, int position, Long templateId) throws LoginException
    {
        HttpResponse response = interceptor.doRequest(
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
    public FieldBlockDTO createFieldBlock(String description, String name) throws LoginException
    {
        HttpResponse response = interceptor.doRequest("/api/fieldBlocks", REQUESTTYPE.REQUESTTYPE_POST,
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
    public FieldBlockDTO addItemToFieldBlockLayout(Long customFieldId, Long fieldBlockId, int position) throws LoginException
    {
        HttpResponse response = interceptor.doRequest(
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
