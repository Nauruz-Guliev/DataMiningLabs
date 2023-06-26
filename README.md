# :star: Website category identifier

This script can be used to determine which topic a website belongs to the most.

# :right_anger_bubble: Usage

* Open the project in any IDE for python (preferably PyCharm).
* Download all the necessary libraries.
* Define the url that you need to analize.
```python
URL = "https://cyberleninka.ru/article/c/basic-medicine"
```
* Run the script and see the results. For the link listed above the result will be as follows:
```
Cosine similarity
science: 0.9972582641400416
sports: 0.7905615722471595
news: 0.35355339059327373
shopping: 0.6093531655310358


Jaccard index
science: 0.00340522133938706
news: 0.0
sport: 0.0
shopping: 0.0

```

Notice that [this logic](https://ru.stackoverflow.com/a/1261379) was used for calculating cosine similarity.
