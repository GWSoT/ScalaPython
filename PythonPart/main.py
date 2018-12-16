import requests
import pandas as pd
import json
from generator import generator as generate
url = "http://localhost:9000"

def main():
    df = pd.read_csv('./data/movies.csv')
    # for index, row in df.iterrows():
    #     params = dict(
    #         id=row['Id'],
    #         name=row['Name'],
    #         genre=row['Genre']
    #     )
    #     resp = requests.request("POST", url=url+'/movies', data=json.dumps(params), headers={'Content-Type': 'application/json'})

    schedules_df = pd.read_csv('./data/schedules.csv')
    for index, row in schedules_df.iterrows():
        params = dict(
            id=row['Id'],
            time=row['Time'],
            movieId=row['MovieId'],
            price=row["Price"],
            hallId=row["HallId"]
        )
        resp = requests.request("POST", url=url+'/schedule', data=json.dumps(params), headers={'Content-Type': 'application/json'})


if __name__ == "__main__":
    # generate(1000)
    main()