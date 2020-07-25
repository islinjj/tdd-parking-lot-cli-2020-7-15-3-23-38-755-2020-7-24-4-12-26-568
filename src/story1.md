- 1、  
given:car、parking boy  
when:parkingboy  
then:return ticket
- 2、  
given:a correct ticket、parking boy  
when:parking boy fetch car  
then:return a car
- 3、
given:two correct ticket、parking
when:fetch
then:return two car
- 4  
given:2 Car、1 parking boy  
then:parking 2 Car  
then:return 2 ticket  
- 5、  
given:1 parking boy,1 ticket,2(Car in parking lot)  
when:fetch  
then:a correct car
- 6、
given:wrong ticket、parking boy
when:fetch
then:null
- 7、  
given:no ticket、parking boy  
when:fetch  
then:null car
- 8、  
given:used ticket、parking boy  
when:fetch  
then:null car
- 9、  
given: 1 car,capacity,parking boy  
when:parking  
then:null ticket