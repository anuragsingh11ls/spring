package com.ensat.services;

import com.ensat.entities.Voucher;

public interface VoucherService {

    Iterable<Voucher> listAllVouchers();

    Voucher getVoucherById(Integer id);

    Voucher saveVoucher(Voucher voucher);

    void deleteProduct(Integer id);

}
