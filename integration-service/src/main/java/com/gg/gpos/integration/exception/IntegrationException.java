package com.gg.gpos.integration.exception;

/**
 * This is the root exception class for all exceptions.
 *
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
@SuppressWarnings("serial")
public class IntegrationException extends Exception {

    /**
     * Vendor specific error code
     *
     */
    private final String errorCode;

    /**
     * Exception reference
     *
     */
    private final Throwable linkedException;

    /**
     * Construct a InvoiceNumberException with the specified detail message.  The
     * errorCode and linkedException will default to null.
     *
     * @param message a description of the exception
     */
    public IntegrationException(String message) {
        this( null, message, null );
    }

    /**
     * Construct a InvoiceNumberException with the specified detail message and vendor
     * specific errorCode.  The linkedException will default to null.
     *
     * @param message a description of the exception
     * @param errorCode a string specifying the vendor specific error code
     */
    public IntegrationException(String errorCode, String message) {
        this( errorCode, message, null );
    }

    /**
     * Construct a InvoiceNumberException with a linkedException.  The detail message and
     * vendor specific errorCode will default to null.
     *
     * @param exception the linked exception
     */
    public IntegrationException(Throwable exception) {
        this( null, null, exception );
    }

    /**
     * Construct a InvoiceNumberException with the specified detail message and
     * linkedException.  The errorCode will default to null.
     *
     * @param message a description of the exception
     * @param exception the linked exception
     */
    public IntegrationException(String message, Throwable exception) {
        this( null, message, exception );
    }

    /**
     * Construct a InvoiceNumberException with the specified detail message, vendor
     * specific errorCode, and linkedException.
     *
     * @param message a description of the exception
     * @param errorCode a string specifying the vendor specific error code
     * @param exception the linked exception
     */
    public IntegrationException(String errorCode, String message, Throwable exception) {
        super( message );
        this.errorCode = errorCode;
        this.linkedException = exception;
    }

    /**
     * Get the vendor specific error code
     *
     * @return a string specifying the vendor specific error code
     */
    public String getErrorCode() {
        return this.errorCode;
    }

    /**
     * Get the linked exception
     *
     * @return the linked Exception, null if none exists
     */
    public Throwable getLinkedException() {
        return linkedException;
    }

}
