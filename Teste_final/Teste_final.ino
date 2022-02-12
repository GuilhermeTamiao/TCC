#include <ESP8266WiFi.h>

//Informação da rede WIFI a ser conectada
const char* ssid     = "Gui";
const char* password = "35811316";

//Informação de que qual vaga é responsavel
const int vaga = 1
;

//IP/Site onde se encontra o Banco de Dados
const char* host = "192.168.0.6";


//pinagem dos leds e sesnsore da vaga 1
const int pinoLedVerdeV1 = 2; 
const int pinoLedVermelhoV1 = 4; //troquei o 4 e o 5
const int pinoSensorV1 = 5;  //troquei o 4 e o 5

//pinagem dos leds e sensor da vaga 2
//const int pinoLedAmareloV2 = 14;
//const int pinoLedVermelhoV2 = 12;
//const int pinoSensorV2 = 13;

//0 livre 1 ocupado
int estadoVaga1 = 0;
//int estadoVaga2 = 0;
//int estadoVaga3 = 0;



void setup() {

  pinMode(pinoSensorV1, INPUT); 
  pinMode(pinoLedVerdeV1, OUTPUT);
  pinMode(pinoLedVermelhoV1, OUTPUT);
//  pinMode(pinoSensorV2, INPUT);
//  pinMode(pinoLedAmareloV2, OUTPUT);
//  pinMode(pinoLedVermelhoV2, OUTPUT);


  
  Serial.begin(9600);
  delay(10);

  Serial.println();
  Serial.println();
  Serial.print("Conectando com  ");
  Serial.println(ssid);

 
//Tentando estabelecer a comunicação com o WIFI
  WiFi.begin(ssid, password);
  
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  Serial.println("");
  Serial.println("WiFi conectado");
  Serial.println("Endereco IP: ");
  Serial.println(WiFi.localIP());
}

void loop() {
  //===========================================================
  //  Espaco reservado para a leitura dos sensores

  bool sensor1 = digitalRead(pinoSensorV1);
  
  if(!sensor1){ 
      digitalWrite(pinoLedVerdeV1, HIGH);
      digitalWrite(pinoLedVermelhoV1, LOW);
      estadoVaga1 = 0;
  }
  else{
    digitalWrite(pinoLedVerdeV1, LOW);
    digitalWrite(pinoLedVermelhoV1, HIGH);
    estadoVaga1 = 1;
  }

//  if(!sensor2){
//    digitalWrite(pinoLedAmareloV2, HIGH);
//    digitalWrite(pinoLedVermelhoV2, LOW);
//    estadoVaga2 = 0;
//  }
//  else{
//    digitalWrite(pinoLedAmareloV2, LOW);
//    digitalWrite(pinoLedVermelhoV2, HIGH);
//    estadoVaga2 = 1;
//  }
  //===========================================================

  
  Serial.print("Conectando com ");
  Serial.println(host);
 

  // Criando conexão TCP
  WiFiClient client;

  //porta 80 por ser um servidor WEB
  const int port = 80;
  
  if (!client.connect(host, port)) {
    Serial.println("Falha na conexao");
    return;
  }

  //Criando a URL

 //Casa Man
//  String url = "/htdocs/Estacionamento/Conexao.php?";
//  url += "vaga1=";
//  url += estadoVaga1;
//  url += "vaga2=";
//  url += estadoVaga2;

//Casa Gui
String url = "/TCC/salvar.php?";
  url += "vaga1=";
  url += estadoVaga1;
  url += "&id=";
  url += vaga;
  
  Serial.print("Requisitando URL: ");
  Serial.println(url);
  

  //quando faço a solicitação GET se acrecento o cabeçalho HTTP/1.1 ele salva no banco de dados com as palavras HTTP
  client.print(String("GET ") + url + "\r\n" + "Host: "+ host + "\r\n " + "Conenection: close\r\n\r\n");

  // wait for data to be available
  unsigned long timeout = millis();
  while (client.available() == 0) {
    if (millis() - timeout > 5000) {
      Serial.println(">>> Client Timeout !");
      client.stop();
      //delay(10000);
      return;
    }
  }

  // Read all the lines of the reply from server and print them to Serial
  Serial.println("Recebendo resposta");
  // not testing 'client.connected()' since we do not need to send data here
  while (client.available()) {
    String line = client.readStringUntil('\r');
    //Serial.print(line);

    //tratamento de erro
    if(line.indexOf("Salvo_com_sucesso") != -1){
      Serial.println();
      Serial.println("Salvo com sucesso");
    }
    else if (line.indexOf("Erro_ao_salvar") != -1){
      Serial.println();
      Serial.println("Ocorreu um erro");
      //Pensar em algo para alertar a falha
    }
  }

  // Close the connection
  Serial.println();
  Serial.println("conexao fechada");
  Serial.println();
  client.stop();

//Tempo para proxima solicitação 5 segundos
  //delay(2000);
}
