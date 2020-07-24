- 1、  
given:car、parking boy  
when:parkingboy  
then:return ticket
- 2、  
given:a ticket、parking boy  
when:parking boy fetch car  
then:return a car
- 3、  
given:2 Car、1 parking boy  
then:parking 2 Car  
then:return 2 ticket  
- 4、  
given:1 parking boy,1 ticket,2(Car in parking lot)  
when:fetch  
then:a correct car
- 5、
given:wrong ticket、parking boy
when:fetch
then:"no car"
- 6、  
given:no ticket、parking boy  
when:fetch  
then:"no car"  
- 7、  
given:uesed ticket、parking boy  
when:fetch  
then:"no car"  
- 8、  
given:1 car,the remaining capacity of a parking is 2,parking boy  
when:parking  
then:1 ticket  
- 9、  
given: 1 car,the remaining capacity of a parking is 0,parking boy  
when:parking  
then:"can't parking"