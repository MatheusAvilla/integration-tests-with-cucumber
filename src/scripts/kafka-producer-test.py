from kafka import KafkaProducer
import json
import time

BOOTSTRAP_SERVERS = "localhost:9092"
TOPIC = "pacientes.created"

producer = KafkaProducer(
    bootstrap_servers=BOOTSTRAP_SERVERS,
    value_serializer=lambda v: json.dumps(v).encode("utf-8")
)

def gerar_paciente(i):
    return {
        "id": i,
        "nome": f"Paciente {i}",
        "email": f"paciente{i}@teste.com",
        "ativo": True
    }

print("Enviando 100 mensagens de pacientes...")

for i in range(1, 1001):
    paciente = gerar_paciente(i)

    producer.send(TOPIC, value=paciente)

    print(f"Mensagem enviada: {paciente}")

producer.flush()

print("✅ Envio concluído!")