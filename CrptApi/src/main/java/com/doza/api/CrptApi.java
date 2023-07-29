package com.doza.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class CrptApi {
    public Logger log = LoggerFactory.getLogger(this.getClass().getName());
    private static final String URL = "https://ismp.crpt.ru/api/v3";
    private static final String CREATE = "/Users/daniil/Documents/Java/IDEA Project/HRTasks/CrptApi/create";

    private final Limit limit;

    public CrptApi(TimeUnit timeUnit, int requestLimit) {
        if (requestLimit <= 0) {
            throw new IllegalArgumentException("Should be >0");
        }
        this.limit = new Limit(timeUnit, requestLimit);
    }

    public String createDocument(Document doc, String signature) {
        Converter converterJson = new ConverterJson();
        String docJson = encodeBase64(convert(doc, converterJson));
        Body body = new Body(Document_Format.MANUAL, docJson, Type.LP_INTRODUCE_GOODS, signature);
        String bodyJson = convert(body, converterJson);

        return requestPost(URL.concat(CREATE), bodyJson, ContentType.APPLICATION_JSON);
    }

    private String encodeBase64(String data) {
        return new String(Base64.getEncoder().encode(data.getBytes()));
    }

    private String convert(Object body, Converter converter) {
        return converter.convert(body);
    }

    private String requestPost(String url, String bodyString, ContentType type) {

        Content postResult = null;
        try {
            if (limit.getLimit() >= 0) {
                postResult = Request.Post(url)
                        .bodyString(bodyString, type)
                        .execute().returnContent();
            } else {
                log.info("limit req in this time");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return postResult != null ? postResult.asString() : "";
    }

    public Document newDocument(String doc_id, String doc_status,
                                String doc_type, boolean importRequest, String participant_inn,
                                String producer_inn, String production_date, String production_type,
                                List<Product> products, String reg_date, String reg_number) {

        Description description = this.new Description(participant_inn);

        return this.new Document(description, doc_id, doc_status, doc_type,
                importRequest, participant_inn,
                producer_inn, production_date, production_type,
                products, reg_date, reg_number);
    }

    private class Limit {
        private final TimeUnit timeUnit;
        private final int requestLimit;

        private AtomicInteger limit;
        private AtomicBoolean checkTime;

        public Limit(TimeUnit timeUnit, int requestLimit) {
            this.timeUnit = timeUnit;
            this.requestLimit = requestLimit;
            this.limit = new AtomicInteger(requestLimit);
            this.checkTime = new AtomicBoolean(true);
        }

        int getLimit() {
            if (checkTime.compareAndSet(true, false)) {
                limit = new AtomicInteger(requestLimit);
                new Thread(this::startTimeLimit).start();
            }
            return limit.decrementAndGet();
        }

        private void startTimeLimit() {
            try {
                Thread.sleep(timeUnit.toMillis(1));
                checkTime.set(true);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
    }

    public class Document {

        private Description description;
        private String doc_id;
        private String doc_status;
        private String doc_type;
        private boolean importRequest;
        private String participant_inn;
        private String producer_inn;
        private String production_date;
        private String production_type;
        private List<Product> products;
        private String reg_date;
        private String reg_number;

        public Document() {
        }

        public Document(Description description, String doc_id, String doc_status, String doc_type, boolean importRequest, String participant_inn, String producer_inn, String production_date, String production_type, List<Product> products, String reg_date, String reg_number) {
            this.description = description;
            this.doc_id = doc_id;
            this.doc_status = doc_status;
            this.doc_type = doc_type;
            this.importRequest = importRequest;
            this.participant_inn = participant_inn;
            this.producer_inn = producer_inn;
            this.production_date = production_date;
            this.production_type = production_type;
            this.products = products;
            this.reg_date = reg_date;
            this.reg_number = reg_number;
        }

        public Description getDescription() {
            return description;
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public String getDoc_id() {
            return doc_id;
        }

        public void setDoc_id(String doc_id) {
            this.doc_id = doc_id;
        }

        public String getDoc_status() {
            return doc_status;
        }

        public void setDoc_status(String doc_status) {
            this.doc_status = doc_status;
        }

        public String getDoc_type() {
            return doc_type;
        }

        public void setDoc_type(String doc_type) {
            this.doc_type = doc_type;
        }

        public boolean isImportRequest() {
            return importRequest;
        }

        public void setImportRequest(boolean importRequest) {
            this.importRequest = importRequest;
        }

        public String getParticipant_inn() {
            return participant_inn;
        }

        public void setParticipant_inn(String participant_inn) {
            this.participant_inn = participant_inn;
        }

        public String getProducer_inn() {
            return producer_inn;
        }

        public void setProducer_inn(String producer_inn) {
            this.producer_inn = producer_inn;
        }

        public String getProduction_date() {
            return production_date;
        }

        public void setProduction_date(String production_date) {
            this.production_date = production_date;
        }

        public String getProduction_type() {
            return production_type;
        }

        public void setProduction_type(String production_type) {
            this.production_type = production_type;
        }

        public List<Product> getProducts() {
            return products;
        }

        public void setProducts(List<Product> products) {
            this.products = products;
        }

        public String getReg_date() {
            return reg_date;
        }

        public void setReg_date(String reg_date) {
            this.reg_date = reg_date;
        }

        public String getReg_number() {
            return reg_number;
        }

        public void setReg_number(String reg_number) {
            this.reg_number = reg_number;
        }
    }


    public class Product {
        String certificate_document;
        String certificate_document_date;
        String certificate_document_number;
        String owner_inn;
        String producer_inn;
        String production_date;
        String tnved_code;
        String uit_code;
        String uitu_code;

        public Product() {
        }

        public Product(String certificate_document, String certificate_document_date,
                       String certificate_document_number, String owner_inn, String producer_inn,
                       String production_date, String tnved_code, String uit_code, String uitu_code) {
            this.certificate_document = certificate_document;
            this.certificate_document_date = certificate_document_date;
            this.certificate_document_number = certificate_document_number;
            this.owner_inn = owner_inn;
            this.producer_inn = producer_inn;
            this.production_date = production_date;
            this.tnved_code = tnved_code;
            this.uit_code = uit_code;
            this.uitu_code = uitu_code;
        }

        public String getCertificate_document() {
            return certificate_document;
        }

        public void setCertificate_document(String certificate_document) {
            this.certificate_document = certificate_document;
        }

        public String getCertificate_document_date() {
            return certificate_document_date;
        }

        public void setCertificate_document_date(String certificate_document_date) {
            this.certificate_document_date = certificate_document_date;
        }

        public String getCertificate_document_number() {
            return certificate_document_number;
        }

        public void setCertificate_document_number(String certificate_document_number) {
            this.certificate_document_number = certificate_document_number;
        }

        public String getOwner_inn() {
            return owner_inn;
        }

        public void setOwner_inn(String owner_inn) {
            this.owner_inn = owner_inn;
        }

        public String getProducer_inn() {
            return producer_inn;
        }

        public void setProducer_inn(String producer_inn) {
            this.producer_inn = producer_inn;
        }

        public String getProduction_date() {
            return production_date;
        }

        public void setProduction_date(String production_date) {
            this.production_date = production_date;
        }

        public String getTnved_code() {
            return tnved_code;
        }

        public void setTnved_code(String tnved_code) {
            this.tnved_code = tnved_code;
        }

        public String getUit_code() {
            return uit_code;
        }

        public void setUit_code(String uit_code) {
            this.uit_code = uit_code;
        }

        public String getUitu_code() {
            return uitu_code;
        }

        public void setUitu_code(String uitu_code) {
            this.uitu_code = uitu_code;
        }
    }


    public class Description {
        private String participantInn;

        public Description() {
        }

        public Description(String participantInn) {
            this.participantInn = participantInn;
        }

        public String getParticipantInn() {
            return participantInn;
        }

        public void setParticipantInn(String participantInn) {
            this.participantInn = participantInn;
        }
    }

    class Body {

        private Document_Format document_format;
        private String product_document;
        private Type type;
        private String signature;

        public Body() {
        }

        public Body(Document_Format document_format, String product_document, Type type, String signature) {
            this.document_format = document_format;
            this.product_document = product_document;
            this.type = type;
            this.signature = signature;
        }

        public Document_Format getDocument_format() {
            return document_format;
        }

        public void setDocument_format(Document_Format document_format) {
            this.document_format = document_format;
        }

        public String getProduct_document() {
            return product_document;
        }

        public void setProduct_document(String product_document) {
            this.product_document = product_document;
        }

        public Type getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }
    }

    interface Converter {
        String convert(Object body);
    }

    class ConverterJson implements Converter {
        private ObjectMapper mapper = new ObjectMapper();

        @Override
        public String convert(Object body) {
            String json = null;
            try {
                json = mapper.writeValueAsString(body);
                return json;
            } catch (JsonProcessingException e) {
                log.error(e.getMessage());
            }
            return json != null ? json : "";
        }
    }

    enum Document_Format {
        MANUAL,
        CSV,
        XML
    }

    public enum Type {
        LP_INTRODUCE_GOODS
    }

}
