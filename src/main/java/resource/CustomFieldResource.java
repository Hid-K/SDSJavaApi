package resource;

import dto.CustomFieldDTO;

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
//    CustomFieldDTO updateCustomField(String description, String name, String type);

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
