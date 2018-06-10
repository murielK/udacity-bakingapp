package hr.murielkamgang.mf.data.source;

/**
 * Created by muriel on 24/2/18.
 */

/**
 * Class holder of fieldName + filedValue used to find object in {@link hr.murielkamgang.mf.data.source.base.BaseLocalDataSource}
 */
public abstract class KeyValueHolder {

    public abstract String getFieldName();

    public abstract Object getFieldValue();
}

