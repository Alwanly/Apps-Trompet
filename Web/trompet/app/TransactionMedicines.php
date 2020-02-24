<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class TransactionMedicines extends Model
{
    protected $tables = 'transaction_medicines';
    protected $fillable = ['id_user', 'id_medicines'];

    public function medicines()
    {
        return $this->belongsTo(Medicines::class, 'id_medicines');
    }

    public function users()
    {
        return $this->belongsTo(User::class, 'id_user');
    }
}
