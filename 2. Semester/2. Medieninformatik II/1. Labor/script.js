const container = document.getElementById('lotto-container');
const playBtn = document.getElementById('play-btn');
const resetBtn = document.getElementById('reset-btn');
const resultMessage = document.getElementById('result-message');

//Erstellung der 49 Buttons
for (let i = 1; i <= 49; i++) {
    const btn = document.createElement('button');
    btn.innerText = i;
    btn.classList.add('lotto-button');

    btn.addEventListener('click', function() {
        // Nur klickbar, wenn noch nicht gespielt wurde
        if (resetBtn.style.display === 'none') {
            const ausgewaehlteAnzahl = document.querySelectorAll('.lotto-button.selected').length;

            if (this.classList.contains('selected')) {
                this.classList.remove('selected');
            } else if (ausgewaehlteAnzahl < 6) {
                this.classList.add('selected');
            }

            // Prüfen, ob "Spielen" Button aktiviert werden kann
            const neueAnzahl = document.querySelectorAll('.lotto-button.selected').length;
            playBtn.disabled = (neueAnzahl !== 6);
        }
    });

    container.appendChild(btn);
}

//Die Spiellogik
playBtn.addEventListener('click', () => {
    const gewaehlteButtons = document.querySelectorAll('.lotto-button.selected');
    const gewaehlteZahlen = Array.from(gewaehlteButtons).map(b => parseInt(b.innerText));
    
    //Zufallszahlen generieren (Ziehung)
    const ziehung = [];
    while (ziehung.length < 6) {
        let r = Math.floor(Math.random() * 49) + 1;
        if (!ziehung.includes(r)) ziehung.push(r);
    }

    //Jeden Button prüfen
    const alleButtons = document.querySelectorAll('.lotto-button');
    let treffer = 0;

    alleButtons.forEach(btn => {
        const zahl = parseInt(btn.innerText);
        const istInZiehung = ziehung.includes(zahl);
        const istGewaehlt = btn.classList.contains('selected');

        if (istGewaehlt && istInZiehung) {
            btn.classList.add('correct');
            treffer++;
        } else if (istGewaehlt && !istInZiehung) {
            btn.classList.add('wrong');
        } else if (!istGewaehlt && istInZiehung) {
            //Zeige die nicht gewählten, aber gezogenen Zahlen leicht an
            btn.classList.add('drawn-not-selected');
        }
    });

    resultMessage.innerText = `Du hast ${treffer} Richtige! Die Zahlen waren: ${ziehung.join(', ')}`;
    playBtn.style.display = 'none';
    resetBtn.style.display = 'inline-block';
});

//Reset Funktion
resetBtn.addEventListener('click', () => {
    const alleButtons = document.querySelectorAll('.lotto-button');
    alleButtons.forEach(btn => {
      //Entfernt die Klassen wieder, damit die beim neuen Spiel nicht eingefärbt sind.
        btn.classList.remove('selected', 'correct', 'wrong', 'drawn-not-selected');
        btn.style.border = "1px solid #ccc";
    });
    
    resultMessage.innerText = "";
    playBtn.style.display = 'inline-block';
    playBtn.disabled = true;
    resetBtn.style.display = 'none';
});
