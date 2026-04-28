package team5.Epic_Energy_Services.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team5.Epic_Energy_Services.entities.Invoice;
import team5.Epic_Energy_Services.payloads.InvoiceRequestDTO;
import team5.Epic_Energy_Services.services.InvoiceService;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public Page<Invoice> getAllInvoices(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return invoiceService.findAll(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice saveInvoice(@RequestBody @Validated InvoiceRequestDTO body) {
        return invoiceService.save(body);
    }

    @GetMapping("/{id}")
    public Invoice getInvoiceById(@PathVariable Long id) {
        return invoiceService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable Long id) {
        invoiceService.findByIdAndDelete(id);
    }

    @GetMapping("/filter/amount")
    public Page<Invoice> filterByAmount(
            @RequestParam double min,
            @RequestParam double max,
            @RequestParam(defaultValue = "0") int page) {
        // PASSAGGIO CORRETTO: solo i valori, senza "size:"
        return invoiceService.filterByAmount(min, max, page, 10);
    }

    @GetMapping("/filter/year")
    public Page<Invoice> filterByYear(
            @RequestParam int year,
            @RequestParam(defaultValue = "0") int page) {
        // PASSAGGIO CORRETTO: solo i valori, senza "size:"
        return invoiceService.filterByYear(year, page, 10);
    }

}