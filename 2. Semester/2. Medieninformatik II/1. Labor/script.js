function generateTable() {
    // Get the user input from the text field
    const num_rows = parseInt(document.getElementById("num_rows").value);
    // Create the table element
    const table = document.createElement("table");
    // Create a set to keep track of which numbers have already been used in each row
    let used_nums = [];
    // Generate the table rows
    for (let i = 0; i < num_rows; i++) {
        used_nums = [];
        // Generate 6 random numbers between 1 and 49
        for (let j = 0; j < 6; j++) {
            // Keep generating random numbers until we get one that hasn't already been used in this row
            let num;
            do {
            num = Math.floor(Math.random() * 49) + 1;
            } while (used_nums.includes(num));
            // Add the number to the set of used numbers
            used_nums.push(num);
            //console.log(used_nums);
        }

        // sort the numbers
        // Dies ist etwas tricky, der JavaScript sort sortiert normalerweise alphabetisch
        // Mit der Arrow-Funktion sagen wir, dass zum Vergleich die Integers subtrahierten werden sollen
        // (Dies ist beyond MI1 :-)
        used_nums.sort((a, b) => (a - b));
        
        // test Ausgabe
        // console.log(used_nums);

        // and add them to the table
        // Create a new row
        const row = document.createElement("tr");
        for (let j = 0; j < 6; j++) {
            // Create a new cell and add it to the row
            const cell = document.createElement("td");
            cell.textContent = used_nums[j];
            row.appendChild(cell);
        }

        // Add the row to the table
        table.appendChild(row);
    }
    // Add the table to the document
    document.getElementById("table_container").appendChild(table);
}

const container = document.getElementById('lotto-container');

for (let i = 1; i <= 49; i++) {
  const btn = document.createElement('button');
  btn.innerText = i;
  btn.classList.add('lotto-button');

  
  btn.addEventListener('click', function() {

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