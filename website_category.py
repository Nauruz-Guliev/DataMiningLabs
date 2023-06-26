from nltk.corpus import stopwords
from newspaper import Article
import numpy as np
from fuzzywuzzy import process
from nltk.stem.snowball import RussianStemmer
from numpy import dot
import numpy as np
from numpy import dot
from numpy. linalg import norm

stemmer = RussianStemmer()

#URL = "https://ria.ru/20180205/1513977064.html"

#URL = "https://www.shoppinglive.ru/"

URL = "https://cyberleninka.ru/article/c/basic-medicine"

def get_stopless_words(url):
    article = Article(url)
    article.download()
    article.parse()
    article.nlp()
    keywords = article.keywords
    for stopWord in stopwords.words():
        if stopWord in keywords:
            keywords.remove(stopWord)
    keywords = [stemmer.stem(x) for x in keywords]
    return keywords

def get_words(file):
    with open(file, encoding='utf-8', mode='r') as f:
        f = f.read().split(" ")
        f = [stemmer.stem(x) for x in f]
    return f

def jaccard(list1, list2):
    intersection = len(list(set(list1).intersection(list2)))
    union = (len(list1) + len(list2)) - intersection
    return float(intersection) / union

def cosinus_similarity(list1: list, list2: list):
    return dot(list1, list2)/(norm(list1)*norm(list2))


def calculate_similarity_coeff(word, list):
    stem_l = [stemmer.stem(x) for x in list]
    ingredient = stemmer.stem(word)
    res = process.extractOne(ingredient, stem_l)[1]
    if res >= 70:
        return res
    else:
        return 0

def get_similarity_arr(list1, list2):
    arr = []
    for word in list1:
        arr.append(calculate_similarity_coeff(word, list2))
    return arr

def create_arr_of_hundreds(size: int):
    arr = []
    for x in range(size):
        arr.append(100)
    return arr

    # Press the green button in the gutter to run the script.
if __name__ == '__main__':

    print("\n\nCosine similarity")
    arr_diff = get_similarity_arr(get_stopless_words(URL), get_words("science_words.txt"))
    arr_ideal = create_arr_of_hundreds(arr_diff.__len__())
    print("science: " + str(cosinus_similarity(arr_diff, arr_ideal)))
    arr_diff = get_similarity_arr(get_stopless_words(URL), get_words("sport_words.txt"))
    arr_ideal = create_arr_of_hundreds(arr_diff.__len__())
    print("sports: " + str(cosinus_similarity(arr_diff, arr_ideal)))
    arr_diff = get_similarity_arr(get_stopless_words(URL), get_words("news_words.txt"))
    arr_ideal = create_arr_of_hundreds(arr_diff.__len__())
    print("news: " + str(cosinus_similarity(arr_diff, arr_ideal)))
    arr_diff = get_similarity_arr(get_stopless_words(URL), get_words("shopping_words.txt"))
    arr_ideal = create_arr_of_hundreds(arr_diff.__len__())
    print("shopping: " + str(cosinus_similarity(arr_diff, arr_ideal)))

    print("\n\nJaccard index")
    print("science: " + str(jaccard(get_words("science_words.txt"), get_stopless_words(URL))))
    print("news: " + str(jaccard(get_words("news_words.txt"), get_stopless_words(URL))))
    print("sport: " + str(jaccard(get_words("sport_words.txt"), get_stopless_words(URL))))
    print("shopping: " + str(jaccard(get_words("shopping_words.txt"), get_stopless_words(URL))))



