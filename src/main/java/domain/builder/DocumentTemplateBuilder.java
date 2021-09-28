package domain.builder;


import domain.entity.Document;
import domain.entity.DocumentTemplate;
import domain.entity.template.Template;

/**
 * This builder class created for instantiation of {@link DocumentTemplate} objects.
 * You should to use builder for {@link DocumentTemplate} creation.
 * Example:
 * <pre>{@code
 * // ------------
 * DocumentTemplate newDocument = new DocumentTemplateBuilder()
 *          .setId( 1L )
 *          .setDocument( new Document() )
 *          .setTemplate( new Template() )
 *          .build();
 * //-------------
 * }</pre>
 *
 * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 10-Oct-2020
 */
public class DocumentTemplateBuilder {

    private Long id;
    private Document document;
    private Template template;


    /**
     * Creates an empty builder.
     */
    public DocumentTemplateBuilder() {
        /*
         * Empty constructor for creating new empty instance of a class
         */
    }

    /**
     * Creates builder based on exists {@link DocumentTemplate} instance.
     *
     * @param documentTemplate Instance of {@link DocumentTemplate} which should be used for instantiating of builder.
     */
    public DocumentTemplateBuilder( DocumentTemplate documentTemplate ) {
        id = documentTemplate.getId();
        document = documentTemplate.getDocument();
        template = documentTemplate.getTemplate();
    }


    /**
     * This method creates instance of {@link DocumentTemplate} based on filled data into builder.
     *
     * @return Instance of {@link DocumentTemplate}.
     */
    public DocumentTemplate build() {
        DocumentTemplate documentTemplate = new DocumentTemplate();
        documentTemplate.setId( id );
        documentTemplate.setDocument( document );
        documentTemplate.setTemplate( template );
        return documentTemplate;
    }

    /**
     * Sets ID of future {@link DocumentTemplate} object.
     *
     * @param id Value which should be used.
     *
     * @return Updated instance of builder.
     */
    public DocumentTemplateBuilder setId( Long id ) {
        this.id = id;
        return this;
    }

    /**
     * Sets document of future {@link DocumentTemplate} object.
     *
     * @param document Value which should be used.
     *
     * @return Updated instance of builder.
     */
    public DocumentTemplateBuilder setDocument( Document document ) {
        this.document = document;
        return this;
    }

    /**
     * Sets template of future {@link DocumentTemplate} object.
     *
     * @param template Value which should be used.
     *
     * @return Updated instance of builder.
     */
    public DocumentTemplateBuilder setTemplate( Template template ) {
        this.template = template;
        return this;
    }
}
