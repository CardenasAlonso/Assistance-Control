package com.schoolmgmt.domain.enums;
public enum PerformanceLevel {
    DESTACADO, LOGRADO, EN_PROCESO, EN_INICIO;
    public static PerformanceLevel fromScore(double score) {
        if (score >= 18) return DESTACADO;
        if (score >= 14) return LOGRADO;
        if (score >= 11) return EN_PROCESO;
        return EN_INICIO;
    }
}
