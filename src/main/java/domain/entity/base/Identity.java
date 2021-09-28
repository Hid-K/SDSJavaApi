package domain.entity.base;

import java.io.Serializable;
import java.util.Objects;


/**
 * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 17-Mar-2020
 */
public abstract class Identity<I extends Serializable>
        implements Identifiable<I>, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals( Object other ) {
        if ( this == other ) return true;
        if ( !( other instanceof Identity ) || !getClass().isAssignableFrom( other.getClass() ) ) return false;
        @SuppressWarnings( "unchecked" )
        Identity<I> o = ( Identity<I> ) other;
        if ( !o.canEqual( this ) ) return false;
        if ( null == o.getId() || null == getId() ) return false;
        return Objects.equals( getId(), o.getId() );
    }

    protected boolean canEqual( Object other ) {
        return other.getClass().isAssignableFrom( getClass() );
    }

    @Override
    public int hashCode() {
        return Objects.hashCode( getId() );
    }

    @Override
    public String toString() {
        return "Entity of type " + getClass().getName() + " with id: " + getId();
    }
}
