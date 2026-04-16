const container = document.getElementById('lotto-container');

for (let i = 0; i <= 49; i++) {
  const btn = document.createElement('button');
  btn.innerText = i;
  btn.classList.add('lotto-button');

  
  btn.addEventListener('click', function() {

    const zahl = parseInt(this.innerText);
    const ausgewaehlteAnzahl = document.querySelectorAll('.lotto-button.selected').length;
    
    if (this.classList.contains('selected')) {
        this.classList.remove('selected')
    }

    else if (ausgewaehlteAnzahl < 6) {

        this.classList.add('selected');
    }
    
  });

  container.appendChild(btn);
}