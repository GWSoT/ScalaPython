import pandas as pd
import random
from datetime import datetime
import itertools



def generate_date(elems=1000):
    for _ in range(elems):
        year = random.randint(2015, 2019)
        month = random.randint(1, 12)
        day = random.randint(1, 28)
        yield datetime(year, month, day)


def generate_movie_id(elems=1000):
    for _ in range(elems):
        yield random.randint(1, 100)


def generate_price(elems=1000):
    for _ in range(elems):
        y_ = random.randint(7, 20)
        w_ = random.randint(1, 99)
        yield str(y_) + '.' + str(w_)

def generate_halls(elems=1000):
    for _ in range(elems):
        yield random.randint(1, 5)

def generate_genres(elems=1000):
    genres = ['History', 'Fantasy', 'Comedy', 'Sci-Fi', 'Adventure',
              'Detective', 'Drama', 'Crime', 'Action', 'Mystery', 'Horror',
              'Thriller']


    for _ in range(elems):
        genre = random.choice(genres)
        yield genre

def generate_filmnames(sample, elems=1000):
    names = list(itertools.product(sample, repeat=2))
    for _ in range(elems):
        name = random.choice(names)
        yield name[0] + name[1]


def generator(elems=1000):
    df = pd.DataFrame(columns=['Id', 'Time', 'MovieId', 'Price', 'HallId'])
    df['Id'] = list(range(1, elems +1))
    df['Time'] = list(generate_date(elems))
    df['MovieId'] = list(generate_movie_id(elems))
    df['Price'] = list(generate_price(elems))
    df['HallId'] = list(generate_halls(elems))
    df.to_csv('./data/schedules.csv', index=False)

    film_df = pd.DataFrame(columns=['Id','Name','Genre'])
    film_df['Id'] = list(range(1,elems+1))
    film_df['Name'] = list(generate_filmnames(generate_genres(elems), elems))
    film_df['Genre'] = list(generate_genres(elems))
    film_df.to_csv('./data/movies.csv', index=False)
