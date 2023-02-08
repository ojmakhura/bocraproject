import { DataFieldVO } from '@model/bw/org/bocra/portal/form/submission/data/data-field-vo';

export class RowGroup {
  saved = false;
  row: number | undefined = undefined;
  fields: DataFieldVO[] = [];
}
