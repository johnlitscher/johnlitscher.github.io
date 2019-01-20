const HIGH_CARD = 0;
const PAIR = 1;
const TWO_PAIR = 2;
const THREE_OF_A_KIND = 3;
const STRAIGHT = 4;
const FLUSH = 5;
const FULL_HOUSE = 6;
const FOUR_OF_A_KIND = 7;
const STRAIGHT_FLUSH = 8;
const ROYAL_FLUSH = 9;


//type of hands
const HANDS = [HIGH_CARD,PAIR, TWO_PAIR, THREE_OF_A_KIND, STRAIGHT, FLUSH, FULL_HOUSE, FOUR_OF_A_KIND, STRAIGHT_FLUSH, ROYAL_FLUSH]

function royalFlush(){
}

function straightFlush() {
	var flush = flush();
	var straight = straight();

}

function getStraight(cards) {
	//sort the cards
	var sorted = sort(cards);
	var i;
	var consec = 0;
	var consecutive = [];
	for(i = 0; i < sorted.length; i++) {
		if(consec == 0) {
			consecutive.push(sorted[i]);
		}
		
		card


