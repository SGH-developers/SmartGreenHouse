#include <SPI.h>
#include <Ethernet.h>
byte ip[] = {  192, 168, 0, 2 };
void setup() {
  Serial.begin(9600);
  Ethernet.begin(ip);
  server.begin();

}

void loop() {
  EthernetClient client = server.available();
  if (client){
    String InMessage = client.read();
    if(InMessage == "conect?"){
      client.println("conect!");
      
    }
 
  while (client.connected())
  {
   if (client.available()) {
   
   client.println("HTTP/1.1 200 OK");
   client.println("Content-Type: text/html");
   client.println();
   client.println("<!DOCTYPE html>");
   client.println("<html lang=\"ru\">");
   client.println("<head>");
   client.println("<meta charset=\"UTF-8\">");
   client.println("<title>Home</title>");
   client.println("</head>");
   client.println("<body>");
   client.println("<h1>Home Server</h1>");
   client.println("<p>Свободно памяти: ");
   client.println(freeRam());
   client.println(" Байт</p>");
   client.println("</body>");
   client.println("</html>");
   client.stop();   }
  }
 }


}
