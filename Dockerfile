FROM adoptopenjdk/openjdk15:latest

#Image metadata
LABEL author = "Ashish Deshpande"
LABEL email = "ashishdeshpande123@gmail.com"
LABEL description = "Harry Kart Racing Service"

#Landing directory for app.jar
RUN mkdir /home/app

# Environment port
EXPOSE 8080

#Add app.jar from local to destination in container
ADD target/harry-kart-1.0.0.jar /home/app/app.jar

#Create softlink to make ap.jar executable from anywhere in container
RUN ln -s /home/app/app.jar /bin/app.jar

#Set working directory as bin
WORKDIR /bin

ENTRYPOINT ["java", "-jar", "app.jar"]