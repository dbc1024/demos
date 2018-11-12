package com.ectrip.ticket.model.provider;


/**
 * HotelductId entity. @author MyEclipse Persistence Tools
 */

public class HotelductId  implements java.io.Serializable {


    // Fields    

     private Long ductid;
     private Long hotelid;


    // Constructors

    /** default constructor */
    public HotelductId() {
    }

    
    /** full constructor */
    public HotelductId(Long ductid, Long hotelid) {
        this.ductid = ductid;
        this.hotelid = hotelid;
    }

   
    // Property accessors

    public Long getDuctid() {
        return this.ductid;
    }
    
    public void setDuctid(Long ductid) {
        this.ductid = ductid;
    }

    public Long getHotelid() {
        return this.hotelid;
    }
    
    public void setHotelid(Long hotelid) {
        this.hotelid = hotelid;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof HotelductId) ) return false;
		 HotelductId castOther = ( HotelductId ) other; 
         
		 return ( (this.getDuctid()==castOther.getDuctid()) || ( this.getDuctid()!=null && castOther.getDuctid()!=null && this.getDuctid().equals(castOther.getDuctid()) ) )
 && ( (this.getHotelid()==castOther.getHotelid()) || ( this.getHotelid()!=null && castOther.getHotelid()!=null && this.getHotelid().equals(castOther.getHotelid()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getDuctid() == null ? 0 : this.getDuctid().hashCode() );
         result = 37 * result + ( getHotelid() == null ? 0 : this.getHotelid().hashCode() );
         return result;
   }   





}