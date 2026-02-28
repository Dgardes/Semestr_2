from typing import List, Dict, Tuple #підключення бібліотеки для аннотації типів

rules: Dict[Tuple[str, ...] : str] = {
    ("asphalt", "speed", "long"): "Шосейний велосипед ",
    ("contryside", "hills", "comfort"): "Двопідвіс",
    ("asphalt", "short", "comfort"): "Міський круїзер ",
    ("contryside", "asphalt", "long"): "Гравійник ",
    ("contryside", "hills", "speed"): "Хардтейл ",
    ("short", "compact"): "Складаний велосипед"
}

questions: Dict[str : str] = {
    "Чи плануєте ви їздити по рівній дорозі?" : "asphalt",
    "Чи плануєте кататися по бездоріжжю, лісах чи піску?" : "contryside",
    "Чи важлива для вас максимальна швидкість?" : "speed",
    "Чи збираєтесь їздити по горах?" : "hills",
    "Чи потрібна комфортна посадка?" : "comfort",
    "Чи плануєте поїздки на далекі відстані?" : "long",
    "Велосипед потрібен для коротких поїздок містом?" : "short",
    "Чи важливо, щоб велосипед був компактним та складався?" : "compact",
}

problemFeatures: list[str] = []

def diagnose(rules : Dict[Tuple(str, ...) : str], questions: Dict[str : str]):
    problemIsFounded: bool = False
    
    for question_key, problem_value  in questions.items(): #проходимо по кожній парі з словника питань
        if askQuestion(question_key, False):
            problemFeatures.append(problem_value)
    for problems, result in rules.items(): #проходимо по кожній парі правил
        if all(problem in problemFeatures for problem in problems): #якщо список потреб користувача збігається зі списком характеристик велсипеда,
            print("варіант знайдено:\n" + "вам підходить:" + result)
            problemIsFounded = True
            break
    if problemIsFounded == False:
        print("нажаль, такого універсального варіанту не існує")


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
    print("експертна система для підбору типу велосипеду")
    diagnose(rules, questions)
    finish: str = input()
    
main()