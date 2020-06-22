import requests
from asserts import assert_true, assert_equal, assert_raises

def main():
    print("Executing request...")
    print("Converting numbers..")

    payload = {'number': '123412341224'}
    r = requests.get('http://localhost:8080/num_to_english', params=payload)
    print("Converted 123412341224 to", r.text)
    assert_equal("one hundred twenty three billion four hundred twelve million three hundred forty one thousand two hundred twenty four", r.text)
    assert_equal(200, r.status_code)

    payload = {'number': '1'}
    r = requests.get('http://localhost:8080/num_to_english', params=payload)
    print("Converted 1 to", r.text)
    assert_equal("one", r.text)
    assert_equal(200, r.status_code)

    payload = {'number': '19'}
    r = requests.get('http://localhost:8080/num_to_english', params=payload)
    print("Converted 19 to", r.text)
    assert_equal("nineteen", r.text)
    assert_equal(200, r.status_code)

    payload = {'number': '28000'}
    r = requests.get('http://localhost:8080/num_to_english', params=payload)
    print("Converted 28000 to", r.text)
    assert_equal("twenty eight thousand", r.text)
    assert_equal(200, r.status_code)

    payload = {'number': '$28000'}
    r = requests.get('http://localhost:8080/num_to_english', params=payload)
    print("Attempting to convert $28000  and expecting a currency error", r.text)
    assert_equal(422, r.status_code)

    payload = {'number': '.28000'}
    r = requests.get('http://localhost:8080/num_to_english', params=payload)
    print("Attempting to convert .28000  and expecting a currency error", r.text)
    assert_equal(422, r.status_code)

    payload = {'number': '/28000'}
    r = requests.get('http://localhost:8080/num_to_english', params=payload)
    print("Attempting to convert /28000  and expecting a currency error", r.text)
    assert_equal(422, r.status_code)



    print("Integration successful.")




if __name__ == "__main__":
    main()

print("Done")