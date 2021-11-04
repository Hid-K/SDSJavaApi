# SmartDocumentStorage java api



------

This library implements rest requests to SDS server.

------

## Example:

```java
public class main
{
  public static void main(String[] args)
  {
    SDSapi api ("yourHardPassword", "yourBeatyfulUsername");
    if(api.status() != SDSStatuses.allright)
    {
      System.out.println("SDSapi ERROR:" + api.status());
    }

    CustomFieldDTO createdCF = api.createCustomField("CF description", "CF name", "string");	
    FieldBlockDTO createdFB = api.createFieldBlock("FB description", "FB name");

    createdFieldBlock = api.addItemToFieldBlockLayout(createdCF.getId(), createdFB.getId(), 0);

    TemplateDTO createdTemplate = api.createTemplate("Template description", "Te name");
    createdTemplate = api.addItemToTemplateLayout(createdFB.getId(), 0,
                                                  createdTemplate.getId());


    DocumentDTO createdDocument = api.createDocument({createdTemplate});

    createdCF = api.setCustomFieldValue(createdCF.getId(), createdDocument.getId(),
                                        "CF value");
    System.out.println(api.getCustomFieldValue(createdCF.getId(), createdDocument.getId()));
  }
}
```



------

ToDo:

* [x] Add SDS DTO's as imports from SDS original repo
* [x] Add `created` field value generation in `createDocument()`

Realized methods:

* [x] Document:
  * [x] createDocument
  * [x] updateDocument
  * [x] getDocument
  * [x] deleteDocument
  * [x] getAllDocuments
* [ ] Custom field:
  * [x] createCustomField
  * [ ] updateCustomField
  * [ ] getAvailableCustomFieldTypes
  * [x] getCustomFieldValue
  * [x] setCustomFieldValue
  * [ ] getCustomField
  * [ ] deleteCustomField
  * [ ] getAllCustomFields
* [ ] Template:
  * [x] assignTemplateToDocument
  * [ ] getDocumentsAttachedWithTemplate
  * [ ] getTemplatesAssignedWithDocument
  * [ ] getTemplateDefaultCustomFields
  * [ ] addDefaultCustomField
  * [ ] deleteDefaultCustomField
  * [ ] moveDefaultCustomField
  * [ ] getTemplateLayout
  * [x] addItemToTemplateLayout
  * [ ] deleteItemFromTemplateLayout
  * [ ] moveItemInTemplateLayout
  * [ ] getAllTemplates
  * [x] createTemplate
  * [ ] updateTemplate
  * [ ] getTemplate
  * [ ] deleteTemplate
  * [ ] unassignTemplateFromDocument
* [ ] Field block
  * [ ] getFieldBlockLayout
  * [x] addItemToFieldBlockLayout
  * [ ] deleteItemFromFieldBlockLayout
  * [ ] moveItemInFieldBlockLayout
  * [ ] getAllFieldBlock
  * [x] createFieldBlock
  * [ ] updateFieldBlock
  * [ ] getFieldBlock
  * [ ] deleteFieldBlock
  * [ ] getAvailableCustomFieldsForAddingToFieldBLock
  * [ ] getFieldBlocksRelatedWithCustomField
  * [ ] getRelatedTemplates