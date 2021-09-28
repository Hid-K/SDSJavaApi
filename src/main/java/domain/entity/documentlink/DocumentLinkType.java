package domain.entity.documentlink;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 23-Aug-2021
 */
public class DocumentLinkType implements Serializable {

    private static final long serialVersionUID = 1676968583574974447L;

    private Long id;

    private String linkTypeName;

    private String description;

    private String outwardDirectionName;

    private String inwardDirectionName;


    public DocumentLinkType() {
    }


    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getLinkTypeName() {
        return linkTypeName;
    }

    public void setLinkTypeName( String linkTypeName ) {
        this.linkTypeName = linkTypeName;
    }

    public String getOutwardDirectionName() {
        return outwardDirectionName;
    }

    public void setOutwardDirectionName( String outwardDirectionName ) {
        this.outwardDirectionName = outwardDirectionName;
    }

    public String getInwardDirectionName() {
        return inwardDirectionName;
    }

    public void setInwardDirectionName( String inwardDirectionName ) {
        this.inwardDirectionName = inwardDirectionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        DocumentLinkType that = ( DocumentLinkType ) o;
        return Objects.equals( id, that.id ) && Objects.equals( linkTypeName, that.linkTypeName ) && Objects.equals( description, that.description ) && Objects.equals( outwardDirectionName, that.outwardDirectionName ) && Objects.equals( inwardDirectionName, that.inwardDirectionName );
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, linkTypeName, description, outwardDirectionName, inwardDirectionName );
    }
}
