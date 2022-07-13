import { DataFieldVO } from "@model/bw/org/bocra/portal/form/submission/data/data-field-vo";

export class RowGroup {
    row: number | undefined = undefined;
    fields: DataFieldVO[] = [];
}