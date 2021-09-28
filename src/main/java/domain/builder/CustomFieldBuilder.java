package domain.builder;


import domain.entity.CustomField;

/**
 * This builder class created for instantiation of {@link CustomField} objects.
 * You should to use builder for {@link CustomField} creation.
 * Example:
 * <pre>{@code
 * // ------------
 * CustomField newCustomField = new CustomFieldBuilder()
 *          .setId( 1L )
 *          .setName( "Example name" )
 *          .setDescription( "This is example CustomField object" )
 *          .setCustomFieldType( "example.type" )
 *          .build();
 * //-------------
 * }</pre>
 *
 * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 10-Oct-2020
 */
public class CustomFieldBuilder {


    private Long id;
    private String name;
    private String description;
    private String customFieldType;


    /**
     * Creates an empty builder.
     */
    public CustomFieldBuilder() {
        /*
         * Empty constructor for creating new empty instance of a class
         */
    }

    /**
     * Creates builder based on exists {@link CustomField} instance.
     *
     * @param customField Instance of {@link CustomField} which should be used for instantiating of builder.
     */
    public CustomFieldBuilder( CustomField customField ) {
        id = customField.getId();
        name = customField.getName();
        description = customField.getDescription();
        customFieldType = customField.getCustomFieldType();
    }

    public static CustomFieldBuilder getBuilder() {
        return new CustomFieldBuilder();
    }


    /**
     * This method creates instance of {@link CustomField} based on filled data into builder.
     *
     * @return Instance of {@link CustomField}.
     */
    public CustomField build() {
        CustomField customField = new CustomField();
        customField.setId( id );
        customField.setName( name );
        customField.setDescription( description );
        customField.setCustomFieldType( customFieldType );
        return customField;
    }

    /**
     * Sets ID of future {@link CustomField} object.
     *
     * @param id Value which should be used.
     *
     * @return Updated instance of builder.
     */
    public CustomFieldBuilder setId( Long id ) {
        this.id = id;
        return this;
    }

    /**
     * Sets name of future {@link CustomField} object.
     *
     * @param name Value which should be used.
     *
     * @return Updated instance of builder.
     */
    public CustomFieldBuilder setName( String name ) {
        this.name = name;
        return this;
    }

    /**
     * Sets description of future {@link CustomField} object.
     *
     * @param description Value which should be used.
     *
     * @return Updated instance of builder.
     */
    public CustomFieldBuilder setDescription( String description ) {
        this.description = description;
        return this;
    }

    /**
     * Sets customFieldType of future {@link CustomField} object.
     *
     * @param customFieldType Value which should be used.
     *
     * @return Updated instance of builder.
     */
    public CustomFieldBuilder setCustomFieldType( String customFieldType ) {
        this.customFieldType = customFieldType;
        return this;
    }
}
