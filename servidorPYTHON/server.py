import socket
from pyfirmata import Arduino, SERVO
from time import sleep

HOST = '192.168.43.99'
PORT = 1234        

board = Arduino('COM4')

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.bind((HOST, PORT))
    while(True):
        s.listen()
        conn, addr = s.accept()
        with conn:
            print('Connected by', addr)
            while True:
                data = conn.recv(1024)
                if not data:
                    break
                mensagem = data.decode("utf-8")
                if "traz" in mensagem:
                    board.digital[13].write(1)
                    print("traz")

                if "frente" in mensagem:
                    board.digital[12].write(1)
                    mensagem = ""
                    print("frente")

                if "nada" in mensagem:
                    board.digital[13].write(0)
                    board.digital[12].write(0)
                    print("Apagar todos")









            
