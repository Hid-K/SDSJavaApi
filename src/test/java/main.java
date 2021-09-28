import dto.CustomFieldDTO;
import dto.DocumentDTO;
import dto.template.FieldBlockDTO;
import dto.template.TemplateDTO;

import java.io.IOException;
import java.util.Arrays;

public class main
{
    public static void main(String[] args) throws IOException
    {
        SDSapi api = new SDSapi("admin", "admin", "http://192.168.88.239:8080");

        CustomFieldDTO createdCF = api.createCustomField("CF description", "CF name",
                CustomFieldTypeKey.string);
        System.out.println(createdCF);

        TemplateDTO createdTe = api.createTemplate("Test template", "Template name");
        System.out.println(createdTe);

        FieldBlockDTO createdFB = api.createFieldBlock("Test FB", "FB name");
        System.out.println(createdFB);

        createdFB = api.addItemToFieldBlockLayout(createdCF.getId(), createdFB.getId(), 0);
        System.out.println(createdFB);

        createdTe = api.addItemToTemplateLayout(createdFB.getId(), 0, createdTe.getId());
        System.out.println(createdTe);

        DocumentDTO createdDocument = api.createDocument(new Long[]{createdTe.getId()});
        System.out.println(createdDocument);

        createdDocument = api.getDocument(createdDocument.getId());
        createdDocument = api.updateDocument(createdDocument);

        System.out.println(Arrays.deepToString(api.getAllDocuments(1,10)));

        System.out.println(api.setCustomFieldValue(createdCF.getId(), createdDocument.getId(), "10"));
        System.out.println(api.getCustomFieldValue(createdCF.getId(), createdDocument.getId()));

        api.deleteDocument(createdDocument.getId());

        System.out.println(api.getDocument(createdDocument.getId()));
    }
}
