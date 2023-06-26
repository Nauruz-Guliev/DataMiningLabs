
# Load libraries
import pandas as pd
from six import StringIO
from sklearn.tree import DecisionTreeClassifier
from sklearn.model_selection import train_test_split
from sklearn import metrics
from sklearn.tree import export_graphviz
from IPython.display import Image
import pydotplus

col_names = ['pregnant', 'glucose', 'bp', 'skin', 'insulin', 'bmi', 'pedigree', 'age', 'label']
pima = pd.read_csv("diabetes.csv", header=None, names=col_names)

def fun():
    feature_cols = ['pregnant', 'insulin', 'bmi', 'age', 'glucose', 'bp', 'pedigree', 'skin']
    X = pima[feature_cols]
    y = pima.label
    for criterion in ['gini', 'entropy', 'log_loss']:
        X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3, random_state=1)
        clf = DecisionTreeClassifier(criterion=criterion)
        clf = clf.fit(X_train, y_train)
        y_pred = clf.predict(X_test)
        accuracy = metrics.accuracy_score(y_test, y_pred)
        dot_data = StringIO()
        print(f"{criterion.capitalize()} criterion: Accuracy on test data = {accuracy:.3f}")

        export_graphviz(clf, out_file=dot_data,
                    filled=True, rounded=True,
                    special_characters=True, feature_names=feature_cols, class_names=['0', '1'])
        graph = pydotplus.graph_from_dot_data(dot_data.getvalue())
        graph.write_png('diabetes_' + criterion + '.png')
        Image(graph.create_png())

if __name__ == '__main__':
    fun()

