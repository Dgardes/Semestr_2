from typing import List, Dict, Tuple

rules: Dict[Tuple[str, ...] : str] = {
    ("причина1", "причина2") : "результат 1",
    ("причина3", "причина4") : "результат 2",
    ("причина5", "причина6") : "результат 3",
    ("причина7", "причина8") : "результат 4",
    ("причина9", "причина10") : "результат 5"
}

questions: Dict[str : str] = {
    "питання1" : "причина1",
    "питання2" : "причина2",
    "питання3" : "причина3",
    "питання4" : "причина4",
    "питання5" : "причина5",
    "питання6" : "причина6",
    "питання7" : "причина7",
    "питання8" : "причина8",
    "питання9" : "причина9",
    "питання10" : "причина10"
}

problemFeatures: list[str] = []

def diagnose(rules : Dict[Tuple(str, ...) : str], questions: Dict[str : str]):
    problemIsFounded: bool = False
    
    for question_key, problem_value  in questions.items():
        if askQuestion(question_key, False):
            problemFeatures.append(problem_value)
    for problems, result in rules.items():
        if all(problem in problemFeatures for problem in problems):
            print("Проблему знайдено:\n" + "проблема:" + result)
            problemIsFounded = True
            break
    if problemIsFounded == False:
        print("проблему не знайдено")


def askQuestion(question_key: str, error: bool):
    
    if error == True:
        print("Ви ввели неправильне значення відповіді, спробуйте ще раз")
    
    print(question_key)
    print("введіть Y(так) або N(ні)")
    answer: str = input()
    if answer.lower() == "y":
        return True
    elif answer.lower() == "n":
        return False
    else: return askQuestion(question_key, True)

def main():
    print("експертна система для")
    diagnose(rules, questions)
    return 0
    