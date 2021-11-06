package resource;

import dto.CustomFieldDTO;
import dto.CustomFieldTypeKey;

import javax.security.auth.login.LoginException;


/**
 * Represents a REST API related with CustomFields
 *
 * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 28-Sep-2021
 */
public interface CustomFieldResource
{

    /**
     * Creates a new CustomField in the system.
     * @param description Short description for new CustomField.
     * @param name Display name of CustomField.
     * @param type CustomField type-key <a href="https://gitlab.com/ooo-laptev/sds/-/wikis/User-documentation">(check documentation)</a>
     * @return Returns a DTO of created CustomField
     */
    CustomFieldDTO createCustomField( String description, String name, String type ) throws LoginException;

    /**
     * Updates existing CustomField in the system.
     * @param description Short description for new CustomField.
     * @param name Display name of CustomField.
     * @param type CustomField type-key <a href="https://gitlab.com/ooo-laptev/sds/-/wikis/User-documentation">(check documentation)</a>
     * @return Returns a DTO of created CustomField
     */
    CustomFieldDTO updateCustomField(String description, String name, String type, Long id) throws LoginException;

//    CustomFieldTypeKey[] getAvailableCustomFieldTypes();

    /**
     * Returns custom field from the system
     * @param id custom field id
     * @return Returns a DTO of CustomField
     */
    CustomFieldDTO getCustomField(Long id) throws LoginException;

    /**
     * Deletes custom field from the system
     * @param id is of deletable custom field
     */
    void deleteCustomField(Long id) throws LoginException;

    /**
     * Getting list of all custom fields from the system.
     * @param pageNumber Page number of the requested page
     * @param pageSize Size of a page
     * @return list of all custom fields from the system.
     */
    CustomFieldDTO[] getAllCustomFields(int pageNumber, int pageSize) throws LoginException;

    /**
     * Getting all available types of custom fields from the system
     * @return Array of all available custom field types.
     * @throws LoginException
     */
    String[] getAvailableCustomFieldTypes() throws LoginException;

    /**
     * Sets a value for CustomField-Document relationship.
     * @param customFieldId ID of CustomField.
     * @param documentId ID of the Document.
     * @param value Some value.
     * @return Returns a saved value
     */
    String setCustomFieldValue( Long customFieldId, Long documentId, String value ) throws LoginException;

    /**
     * Provides a value for Document-CustomField pair.
     * @param customFieldId CustomField ID.
     * @param documentId Document ID.
     * @return A value.
     */
    String getCustomFieldValue( Long customFieldId, Long documentId ) throws LoginException;
}
