# Импортируем необходимые библиотеки
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
import os
from scipy.stats import ttest_1samp, stats
from sklearn.metrics import mean_absolute_error
from scipy.stats import ttest_rel
from scipy.stats import norm
from scipy.stats import multivariate_normal as mn
from sklearn.linear_model import LinearRegression
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import PolynomialFeatures
from sklearn.svm import SVC

basketball_players = pd.read_csv("dataset.csv")

normal_size = 50


def calculate_draw_dependency_linear(x_index, y_index, x_title, y_title, plot_title):
    X_train, X_test, y_train, y_test = train_test_split(basketball_players[[x_index]], basketball_players[[y_index]],
                                                        test_size=.2)

    model = LinearRegression()
    model.fit(X_train, y_train)
    y_pred = model.predict(X_test)

    mae = mean_absolute_error(y_test, y_pred)
    plt.text(0.1, 40, 'Точность прогноза ' + str(mae), \
             bbox={'facecolor': 'white', 'alpha': 1, 'edgecolor': 'none', 'pad': 1})
    plt.plot(X_test, y_pred)
    plt.scatter(X_test, y_test, color='orange')
    plt.title(plot_title)
    plt.ylabel(y_title)
    plt.xlabel(x_title)
    plt.show()


def calculate_draw_dependency_polynomial(x_index, y_index, x_title, y_title, plot_title):
    x = np.array(basketball_players[x_index].values)
    y = np.array(basketball_players[y_index].values)

    poly = PolynomialFeatures(degree=2)
    X_poly = poly.fit_transform(x.reshape(-1, 1))

    # create and fit regression model
    model = LinearRegression()
    model.fit(X_poly, y)

    # create new data for prediction
    new_x = np.array(x).reshape(-1, 1)
    new_X_poly = poly.fit_transform(new_x)

    # make predictions
    predictions = model.predict(new_X_poly)

    mae = mean_absolute_error(y, predictions)
    plt.text(4.5, .5, 'Точность прогноза ' + str(mae), \
             bbox={'facecolor': 'white', 'alpha': 1, 'edgecolor': 'none', 'pad': 1})
    plt.scatter(x, predictions, color='orange')
    plt.title(plot_title)
    plt.ylabel(y_title)
    plt.xlabel(x_title)
    plt.show()


def calculate_svm(x_index, y_index, x_title, y_title, plot_title):
    kernels = ['linear', 'poly', 'rbf', 'sigmoid']
    for kernel in kernels:
        model = SVC(kernel=kernel, C=1.0)
        y_refactored = [int(x * 100) for x in np.array(basketball_players[y_index].values)]
        model.fit(basketball_players[x_index].values.reshape(-1, 1), y_refactored)
        y_pred = model.predict(np.array(basketball_players[x_index].values).reshape(-1, 1))

        # Evaluate the model performance on the test data

        plt.plot(basketball_players[x_index].values, y_pred)
        accuracy = model.score(np.array(basketball_players[x_index].values).reshape(-1, 1), y_refactored)
        print("Kernel: {}, Test Accuracy: {}".format(kernel, accuracy))


if __name__ == '__main__':
    calculate_draw_dependency_linear("gp", "usg_pct", "Количество игр игрока за сезон",
                                     "Мера эффективности броска игрока",
                                     "Зависимость эффективности броска от количества игр")
    calculate_draw_dependency_polynomial("gp", "usg_pct", "Количество игр игрока за сезон",
                                         "Мера эффективности броска игрока",
                                         "Зависимость эффективности броска от количества игр")
    calculate_svm("gp", "usg_pct", "Количество игр игрока за сезон", "Мера эффективности броска игрока",
                  "Зависимость эффективности броска от количества игр")
