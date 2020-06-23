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

        payload = {'number': '456789012345678901234567890123456789012345678901234567890123456789'}
        r = requests.get('http://localhost:8080/num_to_english', params=payload)
        print("Converted 456789012345678901234567890123456789012345678901234567890123456789 to", r.json()["num_in_english"])
        assert_equal("four hundred fifty six vigintillion seven hundred eighty nine novemdecillion twelve octodecillion three hundred forty five septendecillion six hundred seventy eight sexdecillion nine hundred one quindecillion two hundred thirty four quattuordecillion five hundred sixty seven tredecillion eight hundred ninety duodecillion one hundred twenty three undecillion four hundred fifty six decillion seven hundred eighty nine nonillion twelve octillion three hundred forty five septillion six hundred seventy eight sextillion nine hundred one quintillion two hundred thirty four quadrillion five hundred sixty seven trillion eight hundred ninety billion one hundred twenty three million four hundred fifty six thousand seven hundred eighty nine", r.json()["num_in_english"])
        assert_equal(200, r.status_code)

        payload = {'number': '4567890123456789012345678901234567890123456789012345678901234567890'}
        r = requests.get('http://localhost:8080/num_to_english', params=payload)
        print("Attempting to convert 4567890123456789012345678901234567890123456789012345678901234567890 and expecting a number to large error", r.json()["num_in_english"])
        assert_equal(422, r.status_code)


        payload = {'number': '$28000'}
        r = requests.get('http://localhost:8080/num_to_english', params=payload)
        print("Attempting to convert $28000 and expecting a currency error", r.json()["status"])
        assert_equal(422, r.status_code)

        payload = {'number': '.28000'}
        r = requests.get('http://localhost:8080/num_to_english', params=payload)
        print("Attempting to convert .28000 and expecting a decimal error", r.json()["status"])
        assert_equal(422, r.status_code)

        payload = {'number': '/28000'}
        r = requests.get('http://localhost:8080/num_to_english', params=payload)
        print("Attempting to convert /28000 and expecting a fraction error", r.json()["status"])
        assert_equal(422, r.status_code)

        print("Integration successful.")

    except:
        print("An error occurred")
        raise
        sys.exit(1)



if __name__ == "__main__":
    main()

print("Done")