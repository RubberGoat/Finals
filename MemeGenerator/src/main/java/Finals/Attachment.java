/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Finals;

import java.sql.Blob;

/**
 *
 * @author aldo_
 */
public class Attachment {
    private int attachment_id;
    private String attachment_name;
    private Blob attachment_data;

    public Attachment(int attachment_id, String attachment_name, Blob attachment_data) {
        this.attachment_id = attachment_id;
        this.attachment_name = attachment_name;
        this.attachment_data = attachment_data;
    }

    

    /**
     * @return the attachment_name
     */
    public String getAttachment_name() {
        return attachment_name;
    }

    /**
     * @param attachment_name the attachment_name to set
     */
    public void setAttachment_name(String attachment_name) {
        this.attachment_name = attachment_name;
    }

    /**
     * @return the attachment_data
     */
    public Blob getAttachment_data() {
        return attachment_data;
    }

    /**
     * @param attachment_data the attachment_data to set
     */
    public void setAttachment_data(Blob attachment_data) {
        this.attachment_data = attachment_data;
    }

    /**
     * @return the attachment_id
     */
    public int getAttachment_id() {
        return attachment_id;
    }

    /**
     * @param attachment_id the attachment_id to set
     */
    public void setAttachment_id(int attachment_id) {
        this.attachment_id = attachment_id;
    }

    @Override
     public String toString(){
        return getAttachment_id() + " " + getAttachment_name();
    }
  
    
}
