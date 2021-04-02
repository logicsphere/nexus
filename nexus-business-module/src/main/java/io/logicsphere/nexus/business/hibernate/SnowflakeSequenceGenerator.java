package io.logicsphere.nexus.business.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import xyz.downgoon.snowflake.Snowflake;

import java.io.Serializable;

public class SnowflakeSequenceGenerator extends SequenceStyleGenerator {

    private static Snowflake snowflake = null;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return snowflake == null ? null : snowflake.nextId();
    }

    public static void setSnowflake(Snowflake snowflake) {
        SnowflakeSequenceGenerator.snowflake = snowflake;
    }
}
