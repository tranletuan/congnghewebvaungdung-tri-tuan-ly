/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
COMMON ={
     activeMenu : function(id)
    {
        jQuery(".navbar .nav li").each(function() {
            jQuery(this).removeClass("active");
            var this_id = jQuery(this).attr("id");
            if(this_id == id){
                jQuery(this).addClass("active");
            }
         });
        
    }
    
    
};


