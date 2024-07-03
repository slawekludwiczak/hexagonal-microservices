import './style.css';
import Icon from './icon.png';
import _ from 'lodash';
import Data from './data.xml';
import Notes from './data.csv';
import Keycloak from 'keycloak-js';

const keycloak = new Keycloak({
    url: 'http://keycloak:8080',
    realm: 'priceflow',
    clientId: 'frontend-app'
});

try {
    const authenticated = await keycloak.init({
        onLoad: 'check-sso'
    });
    console.log(`User is ${authenticated ? 'authenticated' : 'not authenticated'}`);
    console.log(keycloak.token);
} catch (error) {
    console.error('Failed to initialize adapter:', error);
}

function component() {
    const element = document.createElement('div');

    // Lodash, currently included via a script, is required for this line to work
    element.innerHTML = _.join(['Hello', 'webpackx'], ' ');
    element.classList.add('hello');
    // Add the image to our existing div.
    const myIcon = new Image();
    myIcon.src = Icon;

    element.appendChild(myIcon);

    console.log(Data);
    console.log(Notes);

    return element;
}

document.body.appendChild(component());