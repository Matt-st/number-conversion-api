import requests
import sys
from asserts import assert_true, assert_equal, assert_raises

def main():
    print("Executing request...")
    print("Converting numbers..")
    try:
        payload = {'number': '123412341224'}
        r = requests.get('http://localhost:8080/num_to_english', params=payload)

        print("Converted 123412341224 to", r.json()["num_in_english"])
        assert_equal("one hundred twenty three billion four hundred twelve million three hundred forty one thousand two hundred twenty four", r.json()["num_in_english"])
        assert_equal(200, r.status_code)

        payload = {'number': '1'}
        r = requests.get('http://localhost:8080/num_to_english', params=payload)
        print("Converted 1 to", r.json()["num_in_english"])
        assert_equal("one", r.json()["num_in_english"])
        assert_equal(200, r.status_code)

        payload = {'number': '19'}
        r = requests.get('http://localhost:8080/num_to_english', params=payload)
        print("Converted 19 to", r.json()["num_in_english"])
        assert_equal("nineteen", r.json()["num_in_english"])
        assert_equal(200, r.status_code)

        payload = {'number': '28000'}
        r = requests.get('http://localhost:8080/num_to_english', params=payload)
        print("Converted 28000 to", r.json()["num_in_english"])
        assert_equal("twenty eight thousand", r.json()["num_in_english"])
        assert_equal(200, r.status_code)

        payload = {'number': '$28000'}
        r = requests.get('http://localhost:8080/num_to_english', params=payload)
        print("Attempting to convert $28000  and expecting a currency error", r.json()["status"])
        assert_equal(422, r.status_code)

        payload = {'number': '.28000'}
        r = requests.get('http://localhost:8080/num_to_english', params=payload)
        print("Attempting to convert .28000  and expecting a currency error", r.json()["status"])
        assert_equal(422, r.status_code)

        payload = {'number': '/28000'}
        r = requests.get('http://localhost:8080/num_to_english', params=payload)
        print("Attempting to convert /28000  and expecting a currency error", r.json()["status"])
        assert_equal(422, r.status_code)

        print("Integration successful.")

    except:
        print("An error occurred")
        raise
        sys.exit(1)



if __name__ == "__main__":
    main()

print("Done")