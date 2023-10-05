
from kafka import KafkaConsumer, KafkaProducer
import json
import random
import time

departure_ids = []

consumer = KafkaConsumer(
    'departure-ids',
    bootstrap_servers=['localhost:9092'],
    group_id='railway-system',
    value_deserializer=lambda m: json.loads(m.decode('utf-8'))
)

producer = KafkaProducer(
    bootstrap_servers=['localhost:9092'],
    value_serializer=lambda v: json.dumps(v).encode('utf-8')
)

def send_producer_message(updated_departure):
    producer.send('updated-departure', value=updated_departure)
    print(f"Processed departure ID: {updated_departure['id']}")

def process_departure(index, departure):
    choice = random.choices(['Delay', 'Cancel', 'Do nothing'], [0.2, 0.1, 0.7])[0]
    updated_departure = None

    if choice in ['Delay', 'Cancel']:
        updated_departure = {'id': departure['_id']['$oid'], 'status': choice}
        del departure_ids[index]
        send_producer_message(updated_departure)

def show_simulation_input_form():
    while True:
        user_input = input("Enter 'start' to begin script or 'quit' to quit: ")
        if user_input.lower() == "start":
            for index, departure in enumerate(departure_ids):
                process_departure(index, json.loads(departure))

                time.sleep(5)  # Wait for 30 seconds before processing the next departure
                continue
        elif user_input.lower() == "quit":
            print("Quitting...")
            exit(0)
        else:
            print("Invalid input. Please enter 'start' or 'quit'.")

if __name__ == '__main__':
    for message in consumer:
        departure_ids = message.value
        print(f"Processed departure IDs: {departure_ids}")
        show_simulation_input_form()
