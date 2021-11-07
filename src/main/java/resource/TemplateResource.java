package resource;

import dto.DocumentDTO;
import dto.template.TemplateDTO;

import javax.security.auth.login.LoginException;


/**
 * Represents a REST API related with Templates
 *
 * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 28-Sep-2021
 */
public interface TemplateResource
{
    String endpoint = "/api/templates";

    /**
     * Creates new Template.
     * @param description Short description for the Template.
     * @param name Display name for new Template.
     * @return Returns a DTO of Created Template
     */
    TemplateDTO createTemplate( String description, String name) throws LoginException;

    /**
     * Assigns specified Template to existing Document.
     * @param documentId Document ID.
     * @param templateId Template ID.
     * @return Returns a DTO of assigned Document.
     */
    DocumentDTO assignTemplateToDocument( Long documentId, Long templateId) throws LoginException;

    /**
     * Adds FieldBlock to the Template.
     * @param fieldBlockId ID of FieldBlock which should be added.
     * @param templateId ID of Template where FieldBLock should be added.
     * @param position Position of new item in Template.
     * @return Returns a DTO of assigned Template.
     */
    TemplateDTO addItemToTemplateLayout(Long fieldBlockId, int position, Long templateId) throws LoginException;
}
