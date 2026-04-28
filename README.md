```
//--------------POSTMAN - FILTRI--------------------//

Filtro Data primo contatto
GET http://localhost:${PORT}/clients/details?createdAt=2025-01-01

Filtro Data ultimo contatto
GET http://localhost:${PORT}/clients/details?lastContactDate=2026-01-01

Filtro Fatturato annuale
GET http://localhost:${PORT}/clients/details?annualRevenue=500000

Filtro per Provincia
GET http://localhost:${PORT}/clients/details?name=Roma

Filtro per tutti i clienti
GET http://localhost:${PORT}/all


//--------------POSTMAN - ORDINE--------------------//

Ordina per nome in ordine Discendente
GET http://localhost:${PORT}/clients/details?sortBy=companyName&direction=DESC

Ordina per nome in ordine Ascendente
GET http://localhost:${PORT}/clients/details?sortBy=companyName&direction=ASC




//--------------------CRUD--------------------------//

POST http://localhost:${PORT}/clients 




