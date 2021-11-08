import dto.CustomFieldDTO;
import dto.DocumentDTO;
import dto.template.FieldBlockDTO;
import dto.template.TemplateDTO;
import interceptor.Interceptor;
import interceptor.JWTAuthInterceptorImpl;
import interceptor.REQUESTTYPE;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import resource.CustomFieldResource;
import resource.DocumentResource;
import resource.FieldBlockResource;
import resource.TemplateResource;
import resource.authentication.LoginVM;

import javax.security.auth.login.LoginException;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
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

    CustomFieldDTO jsonToCustomField(JSONObject obj)
    {
        CustomFieldDTO result = new CustomFieldDTO();

        try
        {
            result.setId(obj.getLong("id"));
        } catch (JSONException e)
        {
            return null;
        }

        try
        {
            result.setDescription(obj.getString("description"));
        } catch (JSONException e)
        {
            result.setDescription(null);
        }

        try
        {
            result.setName(obj.getString("name"));
        } catch (JSONException e)
        {
            result.setName(null);
        }

        try
        {
            result.setType(obj.getString("type"));
        } catch (JSONException e)
        {
            result.setType(null);
        }

        return result;
    }

    DocumentDTO jsonToDocument(JSONObject obj)
    {
        DocumentDTO result = new DocumentDTO();

        try
        {
            result.setId(obj.getLong("id"));
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

        try
        {
            result.setCreated(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX")
                    .parse(obj.getString("created")));
        } catch (ParseException e)
        {
            e.printStackTrace();
            result.setCreated(new Date(0));
        }

        try
        {
            Iterator<Object> iter = obj.getJSONArray("assignedTemplates").iterator();

            List<Long> assignedTemplates = new LinkedList<>();

            for (Iterator<Object> it = iter; it.hasNext(); )
            {
                assignedTemplates.add(Long.valueOf(String.valueOf(it.next())));
            }

            result.setAssignedTemplates(assignedTemplates);
        } catch (JSONException e)
        {
//            e.printStackTrace();
            result.setAssignedTemplates(null);
        }

        return result;
    }

    TemplateDTO jsonToTemplate(JSONObject obj)
    {
        TemplateDTO result = new TemplateDTO();

        try
        {
            result.setId(obj.getLong("id"));
        } catch ( Exception e )
        {
            return null;
        }

        try
        {
            result.setDescription(obj.getString("description"));
        } catch (JSONException e)
        {
            result.setDescription(null);
        }

        try
        {
            result.setName(obj.getString("name"));
        } catch (JSONException e)
        {
            result.setName(null);
        }

        return result;
    }

    FieldBlockDTO jsonToFieldBlock(JSONObject obj)
    {
        FieldBlockDTO result = new FieldBlockDTO();

        try
        {
            result.setId(obj.getLong("id"));
        } catch (Exception e)
        {
            return null;
        }

        try
        {
            result.setName(obj.getString("name"));
        } catch (JSONException e)
        {
            result.setName(null);
        }

        try
        {
            result.setDescription(obj.getString("description"));
        } catch (JSONException e)
        {
            result.setDescription(null);
        }

        return result;
    }

    @Override
    public CustomFieldDTO createCustomField( String description, String name, String type ) throws LoginException
    {
        HttpResponse response = interceptor.doRequest(CustomFieldResource.endpoint, REQUESTTYPE.REQUESTTYPE_POST,
                new JSONObject().put("description", description)
                                .put("id", "null")
                                .put("name", name)
                                .put("type", type).toString());


        if(response != null)
        {
            if (response.statusCode() == 200 ||
                response.statusCode() == 201)
            {
                return jsonToCustomField(new JSONObject(String.valueOf(response.body())));
            } else
            {
                handleNonSuccessfullResponse(response);
                return null;
            }
        } else return null;
    }

    @Override
    public CustomFieldDTO updateCustomField(String description, String name, String type, Long id) throws LoginException
    {
        HttpResponse response = interceptor.doRequest(CustomFieldResource.endpoint, REQUESTTYPE.REQUESTTYPE_PUT,
                new JSONObject().put("description", description)
                        .put("id", id)
                        .put("name", name)
                        .put("type", type).toString());


        if(response != null)
        {
            if (response.statusCode() == 200 ||
                    response.statusCode() == 201)
            {
                return jsonToCustomField(new JSONObject(String.valueOf(response.body())));
            } else
            {
                handleNonSuccessfullResponse(response);
                return null;
            }
        } else return null;
    }

    @Override
    public CustomFieldDTO getCustomField(Long id) throws LoginException
    {
        HttpResponse response = interceptor.doRequest(CustomFieldResource.endpoint+"/"+id, REQUESTTYPE.REQUESTTYPE_GET, null);


        if(response != null)
        {
            if (response.statusCode() == 200 ||
                    response.statusCode() == 201)
            {
                return jsonToCustomField(new JSONObject(String.valueOf(response.body())));

            } else
            {
                handleNonSuccessfullResponse(response);
                return null;
            }
        } else return null;
    }

    @Override
    public void deleteCustomField(Long id) throws LoginException
    {
        interceptor.doRequest(CustomFieldResource.endpoint+"/"+id, REQUESTTYPE.REQUESTTYPE_DELETE, null);
    }

    @Override
    public CustomFieldDTO[] getAllCustomFields(int pageNumber, int pageSize) throws LoginException
    {
        HttpResponse response = interceptor.doRequest(CustomFieldResource.endpoint+"?page="+pageNumber+"&size="+pageSize, REQUESTTYPE.REQUESTTYPE_GET, null);


        if(response != null)
        {
            if (response.statusCode() == 200 ||
                    response.statusCode() == 201)
            {
                List<CustomFieldDTO> result = new LinkedList<>();
                for (Object o : new JSONArray(String.valueOf(response.body())))
                {
                    result.add(jsonToCustomField(new JSONObject(o.toString())));
                }

                return result.toArray(new CustomFieldDTO[0]);
            } else
            {
                handleNonSuccessfullResponse(response);
                return null;
            }
        } else return null;
    }

    @Override
    public String[] getAvailableCustomFieldTypes() throws LoginException
    {
        HttpResponse response = interceptor.doRequest(CustomFieldResource.endpoint+"/availableTypes", REQUESTTYPE.REQUESTTYPE_GET, null);

        if(response != null)
        {
            if (response.statusCode() == 200 ||
                    response.statusCode() == 201)
            {
                return new JSONArray(response.body().toString()).toList().toArray(new String[0]);
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
                CustomFieldResource.endpoint+"/value?customFieldId=" + customFieldId +
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
                CustomFieldResource.endpoint+"/value?customFieldId=" + customFieldId +
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
                DocumentResource.endpoint, REQUESTTYPE.REQUESTTYPE_POST,
                new JSONObject().put("assignedTemplates", assignedTemplates)
                                .put("created",
                                        getSDSFormattedDate(LocalDateTime.now())
                                ).toString());


        if(response != null)
        {
            if (response.statusCode() == 200 ||
                    response.statusCode() == 201)
            {
                return jsonToDocument(new JSONObject(String.valueOf(response.body())));
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
                DocumentResource.endpoint, REQUESTTYPE.REQUESTTYPE_PUT,
                new JSONObject().put("assignedTemplates", document.getAssignedTemplates())
                                .put("created", getSDSFormattedDate(document.getCreated()))
                                .put("id", document.getId()).toString());


        if(response != null)
        {
            if (response.statusCode() == 200 ||
                    response.statusCode() == 201)
            {
                return jsonToDocument(new JSONObject(String.valueOf(response.body())));
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
                DocumentResource.endpoint+"/"+id, REQUESTTYPE.REQUESTTYPE_GET, "");


        if(response != null)
        {
            if (response.statusCode() == 200 ||
                    response.statusCode() == 201)
            {
                return jsonToDocument(new JSONObject(String.valueOf(response.body())));
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
                DocumentResource.endpoint+"/"+id, REQUESTTYPE.REQUESTTYPE_DELETE, "");

        if(response.statusCode() != 200 &&
           response.statusCode() != 201 &&
           response.statusCode() != 204) handleNonSuccessfullResponse(response);
    }


    @Override
    public DocumentDTO[] getAllDocuments(int pageNumber, int pageSize) throws LoginException
    {
        HttpResponse response = interceptor.doRequest(
                DocumentResource.endpoint+"?page=" + pageNumber + "&size="+pageSize,
                         REQUESTTYPE.REQUESTTYPE_GET, "");

        if(response != null)
        {
            if (response.statusCode() == 200 ||
                    response.statusCode() == 201)
            {
                List<DocumentDTO> result = new LinkedList<>();
                for (Object o : new JSONArray(String.valueOf(response.body())))
                {
                    result.add((jsonToDocument(new JSONObject(o.toString()))));
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
        HttpResponse response = interceptor.doRequest(TemplateResource.endpoint, REQUESTTYPE.REQUESTTYPE_POST,
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
                return jsonToTemplate(new JSONObject(String.valueOf(response.body())));
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
        HttpResponse response = interceptor.doRequest(FieldBlockResource.endpoint, REQUESTTYPE.REQUESTTYPE_POST,
                new JSONObject().put("description", description)
                        .put("id", "null")
                        .put("name", name).toString());


        if(response != null)
        {
            if (response.statusCode() == 200 ||
                    response.statusCode() == 201)
            {
                return jsonToFieldBlock(new JSONObject(String.valueOf(response.body())));
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
                return jsonToFieldBlock(new JSONObject(String.valueOf(response.body())));
            } else
            {
                handleNonSuccessfullResponse(response);
                return null;
            }
        } else return null;
    }

}
