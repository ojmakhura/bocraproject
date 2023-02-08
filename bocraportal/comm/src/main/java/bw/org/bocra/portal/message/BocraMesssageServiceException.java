// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by DefaultServiceException.vsl in andromda-spring-cartridge on $springUtils.date. Do not modify by hand!.
//
package bw.org.bocra.portal.message;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * The default exception thrown for unexpected errors occurring
 * within {@link bw.org.bocra.portal.message.BocraMesssageService}.
 */
public class BocraMesssageServiceException
    extends RuntimeException
{
    /**
     * The serial version UID of this class. Needed for serialization.
     */
    private static final long serialVersionUID = -3605680077082490048L;

    /**
     * The default constructor for <code>BocraMesssageServiceException</code>.
     */
    public BocraMesssageServiceException()
    {
        // Documented empty block
    }

    /**
     * Constructs a new instance of <code>BocraMesssageServiceException</code>.
     *
     * @param throwable the parent Throwable
     */
    public BocraMesssageServiceException(Throwable throwable)
    {
        super(findRootCause(throwable));
    }

    /**
     * Constructs a new instance of <code>BocraMesssageServiceException</code>.
     *
     * @param message the throwable message.
     */
    public BocraMesssageServiceException(String message)
    {
        super(message);
    }

    /**
     * Constructs a new instance of <code>BocraMesssageServiceException</code>.
     *
     * @param message the throwable message.
     * @param throwable the parent of this Throwable.
     */
    public BocraMesssageServiceException(String message, Throwable throwable)
    {
        super(message, findRootCause(throwable));
    }

    /**
     * Finds the root cause of the parent exception
     * by traveling up the exception tree
     */
    private static Throwable findRootCause(Throwable th)
    {
        if (th != null)
        {
            // Reflectively get any exception causes.
            try
            {
                Throwable targetException = null;

                // java.lang.reflect.InvocationTargetException
                String exceptionProperty = "targetException";
                if (PropertyUtils.isReadable(th, exceptionProperty))
                {
                    targetException = (Throwable)PropertyUtils.getProperty(th, exceptionProperty);
                }
                else
                {
                    exceptionProperty = "causedByException";
                    //javax.ejb.EJBException
                    if (PropertyUtils.isReadable(th, exceptionProperty))
                    {
                        targetException = (Throwable)PropertyUtils.getProperty(th, exceptionProperty);
                    }
                }
                if (targetException != null)
                {
                    th = targetException;
                }
            }
            catch (Exception ex)
            {
                // just print the exception and continue
                ex.printStackTrace();
            }

            if (th.getCause() != null)
            {
                th = th.getCause();
                th = findRootCause(th);
            }
        }
        return th;
    }

    private Object[] messageArguments;

    /**
     * Gets the message arguments that can be used by message resources (in
     * something like the presentation tier)
     *
     * @return messageArguments
     */
    public Object[] getMessageArguments()
    {
        return this.messageArguments;
    }

    /**
     * Sets the message arguments that can be used by message resources (in
     * something like the presentation tier)
     *
     * @param messageArgumentsIn
     */
    public void setMessageArguments(Object[] messageArgumentsIn)
    {
        this.messageArguments = messageArgumentsIn;
    }
}