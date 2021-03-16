function hello(name: string): void {
    console.log("Hello " + name + "!");
}
let your_name: string = "Yamada";
hello(your_name);


//変数に型を与えて宣言
var originalName: string = '山田太郎山小太郎',
	yen: number = 2000;

//関数の仮引数にも受け入れる型を定義
//{}の前にあるstringの返す値の型の定義
	function payer(uketori: string, daikin: number): string {
	 return '${uketori}が${daikin}円受け取りました'
	}

	payer(originalName, yen)

	//Personインターフェイスをその要素を型とともに定義
interface Person {
	name: string;
	age: number;
}

function intro(person: Person): string {
	return '私の名前は${person.name}です。年齢は${person.age}歳です'
}

var naoki: Person;
naoki.name = '直樹';
naoki.age = 26;

intro(naoki)


class Menu{
	items: Array<string>;
	pages: number


constructor(item_list: Array<string>, total_pages: number) {
   this.items = item_list;
   this.pages = total_pages;
 }
 //Method
 list(): void {
	 console.log("our menu for today");
	 for(var i=0; i<this.items.length; i++) {
		 console.log(this.items[i]);
	 }
 }
}
var sunday = new Menu(["juice", "humbarger", "poteto"], 1);
sunday.list();

//クラスの継承が可能

class OverMenu extends Menu {
	//propertiesは省略可能だがconstructorは定義する必要あり
	constructor(item_list: Array<string>, total_pages: number) {
		super(item_list, total_pages);
	}
}

var overMenu = new OverMenu(["coffe", "cake", "takeout"], 2);
//継承されたメソッドを実行
overMenu.list();



