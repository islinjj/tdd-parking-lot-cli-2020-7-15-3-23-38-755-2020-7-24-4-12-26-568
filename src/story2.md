1、  
given:a wrong ticket,used ticket,parking boy
when:fetch  
then:return "Unrecognized parking ticket."  
2、  
given:null ticket,parking boy  
when:fetch  
then:return "Please provide your parking ticket."  
3、  
given:a car,capacity,parking boy  
when:fetch  
then:return "Not enough position."
