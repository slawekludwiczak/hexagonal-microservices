import './main.css';
import Offers from './offers/offers';

function component() {
    const div = document.createElement('div');
    div.className = 'flex flex-col items-center'
    const h1 = document.createElement('h1');
    h1.innerHTML = 'Priceflow';
    h1.className = 'flex text-8xl font-extrabold text-transparent bg-clip-text bg-gradient-to-r from-purple-400 to-pink-600';
    div.appendChild(h1);
    div.appendChild(new Offers());
    return div;
}

document.body.appendChild(component());