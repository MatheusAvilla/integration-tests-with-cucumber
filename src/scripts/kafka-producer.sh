for i in $(seq 1 1000); do
  echo "{\"nome\": \"Paciente $i\", \"cpf\": \"1234$i\", \"email\": \"paciente$i@email\"}"
done | tee /dev/tty | docker exec -i kafka \
 kafka-console-producer \
  --broker-list localhost:9092 \
  --topic pacientes.created

# "{\"id\": $i, \"nome\": \"Paciente $i\", \"cpf\": \"1234$i\", \"email\": \"paciente$i@email\"}"