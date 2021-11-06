import dto.CustomFieldDTO;
import dto.CustomFieldTypeKey;
import dto.DocumentDTO;
import dto.template.FieldBlockDTO;
import dto.template.TemplateDTO;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class main
{
    public static void main(String[] args) throws IOException, LoginException
    {
        SDSapi api = new SDSapi("admin", "admin", "http://localhost:8080");

        CustomFieldDTO createdCF = api.createCustomField("CF description", "CF name",
                CustomFieldTypeKey.string);

        if(createdCF.getName().equals("CF name") &&
                createdCF.getType().equals(CustomFieldTypeKey.string) &&
                createdCF.getDescription().equals("CF description"))
        {
            System.out.println("PASSED: createCustomField\n==================");
        } else
        {
            System.out.println("ERROR PASSING: createCustomField");
            System.out.println(createdCF);
            System.out.println("==================");
        };

        TemplateDTO createdTe = api.createTemplate("Test template", "Template name");

        if(createdTe.getName().equals("Template name") &&
           createdTe.getDescription().equals("Test template"))
        {
            System.out.println("PASSED: createTemplate\n==================");
        } else
        {
            System.out.println("ERROR PASSING: createTemplate");
            System.out.println(createdTe);
            System.out.println("==================");
        };

        FieldBlockDTO createdFB = api.createFieldBlock("Test FB", "FB name");

        if(createdFB.getName().equals("FB name") &&
           createdFB.getDescription().equals("Test FB"))
        {
            System.out.println("PASSED: createFieldBlock\n==================");
        } else
        {
            System.out.println("ERROR PASSING: createFieldBlock");
            System.out.println(createdFB);
            System.out.println("==================");
        };

        createdFB = api.addItemToFieldBlockLayout(createdCF.getId(), createdFB.getId(), 0);

        if(createdFB.getName().equals("FB name") &&
           createdFB.getDescription().equals("Test FB"))
        {
            System.out.println("PASSED: addItemToFieldBlockLayout\n==================");
        } else
        {
            System.out.println("ERROR PASSING: addItemToFieldBlockLayout");
            System.out.println(createdFB);
            System.out.println("==================");
        };

        createdTe = api.addItemToTemplateLayout(createdFB.getId(), 0, createdTe.getId());

        if(createdTe.getName().equals("Template name") &&
           createdTe.getDescription().equals("Test template"))
        {
            System.out.println("PASSED: createTemplate\n==================");
        } else
        {
            System.out.println("ERROR PASSING: createTemplate");
            System.out.println(createdTe);
            System.out.println("==================");
        };

        DocumentDTO createdDocument = api.createDocument(new Long[]{createdTe.getId()});

        //SDS bug: getAssignedTemplates is always null

        if(createdDocument.getAssignedTemplates() != null &&
           createdDocument.getAssignedTemplates().equals(Arrays.asList(new Long[]{createdTe.getId()})))
        {
            System.out.println("PASSED: createDocument\n==================");
        } else
        {
            System.out.println("ERROR PASSING: createDocument");
            System.out.println(createdDocument);
            System.out.println("==================");
        };

        createdDocument = api.getDocument(createdDocument.getId());

        //SDS bug: getAssignedTemplates is always null

        if(createdDocument.getAssignedTemplates() != null &&
           createdDocument.getAssignedTemplates().equals(Arrays.asList(new Long[]{createdTe.getId()})))
        {
            System.out.println("PASSED: getDocument\n==================");
        } else
        {
            System.out.println("ERROR PASSING: getDocument");
            System.out.println(createdDocument);
            System.out.println("==================");
        };

        createdDocument = api.updateDocument(createdDocument);

        //SDS bug: getAssignedTemplates is always null

        if(createdDocument.getAssignedTemplates() != null &&
           createdDocument.getAssignedTemplates().equals(Arrays.asList(new Long[]{createdTe.getId()})))
        {
            System.out.println("PASSED: updateDocument\n==================");
        } else
        {
            System.out.println("ERROR PASSING: updateDocument");
            System.out.println(createdDocument);
            System.out.println("==================");
        };

        System.out.println(Arrays.deepToString(api.getAllDocuments(1,10)));

        System.out.println(api.setCustomFieldValue(createdCF.getId(), createdDocument.getId(), "10"));
        System.out.println(api.getCustomFieldValue(createdCF.getId(), createdDocument.getId()));

        api.deleteDocument(createdDocument.getId());

        System.out.println(api.getDocument(createdDocument.getId()));

        System.out.println(api.getCustomField(createdCF.getId()));
        System.out.println(api.updateCustomField("description", "name", CustomFieldTypeKey.date, createdCF.getId()));

        System.out.println(api.getCustomField(createdCF.getId()));

        System.out.println(Arrays.deepToString(api.getAllCustomFields(1,8)));
        System.out.println(Arrays.toString(api.getAvailableCustomFieldTypes()));

        api.deleteCustomField(createdCF.getId());

        if(api.getCustomField(createdCF.getId()) == null)
        {
            System.out.println("PASSED: deleteCustomField\n==================");
        } else
        {
            System.out.println("ERROR PASSING: deleteCustomField");
            try
            {
                System.out.println(createdCF);
            } catch (NullPointerException ignored){}
            System.out.println("==================");
        };
    }
}
