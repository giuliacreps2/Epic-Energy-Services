package team5.Epic_Energy_Services.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team5.Epic_Energy_Services.entities.B2bClient;
import team5.Epic_Energy_Services.entities.Invoice;
import team5.Epic_Energy_Services.entities.InvoiceStatus;
import team5.Epic_Energy_Services.exceptions.NotFoundException;
import team5.Epic_Energy_Services.payloads.InvoiceRequestDTO;
import team5.Epic_Energy_Services.repositories.ClientsRepository;
import team5.Epic_Energy_Services.repositories.InvoiceRepository;
import team5.Epic_Energy_Services.repositories.InvoiceStatusRepository;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepo;

    @Autowired
    private ClientsRepository clientsRepo;

    @Autowired
    private InvoiceStatusRepository statusRepo;

    public Invoice save(InvoiceRequestDTO body) {
        B2bClient client = clientsRepo.findById(body.customerId())
                .orElseThrow(() -> new NotFoundException("Cliente con ID " + body.customerId() + " non trovato"));

        InvoiceStatus status = statusRepo.findById(body.statusId())
                .orElseThrow(() -> new NotFoundException("Stato fattura con ID " + body.statusId() + " non trovato"));

        Invoice newInvoice = new Invoice();
        newInvoice.setDate(body.date());
        newInvoice.setAmount(body.amount());
        newInvoice.setNumber(body.number());
        newInvoice.setClient(client);
        newInvoice.setStatus(status);

        return invoiceRepo.save(newInvoice);
    }

    public Page<Invoice> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return invoiceRepo.findAll(pageable);
    }

    public Invoice findById(Long id) {
        return invoiceRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Fattura con ID " + id + " non trovata"));
    }

    public void findByIdAndDelete(Long id) {
        Invoice found = this.findById(id);
        invoiceRepo.delete(found);
    }

    public Page<Invoice> filterByYear(int year, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return invoiceRepo.findByYear(year, pageable);
    }

    public Page<Invoice> filterByAmount(double min, double max, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return invoiceRepo.findByAmountBetween(min, max, pageable);
    }

    public Page<Invoice> filterByClient(UUID clientId, int page, int size) {
        B2bClient client = clientsRepo.findById(clientId)
                .orElseThrow(() -> new NotFoundException("Cliente non trovato"));
        Pageable pageable = PageRequest.of(page, size);
        return invoiceRepo.findByClient(client, pageable);
    }

    public Page<Invoice> filterByStatus(Long statusId, int page, int size) {
        InvoiceStatus status = statusRepo.findById(statusId)
                .orElseThrow(() -> new NotFoundException("Stato non trovato"));
        Pageable pageable = PageRequest.of(page, size);
        return invoiceRepo.findByStatus(status, pageable);
    }

    public Page<Invoice> filterByDate(LocalDate date, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return invoiceRepo.findByDate(date, pageable);
    }
}